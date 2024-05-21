package cn.xiaodong.infra.module.pay.controller.admin.notify;

import cn.hutool.core.collection.CollUtil;
import cn.xiaodong.infra.framework.common.exception.util.ServiceExceptionUtil;
import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.framework.operatelog.core.annotations.OperateLog;
import cn.xiaodong.infra.framework.pay.core.client.PayClient;
import cn.xiaodong.infra.framework.pay.core.client.dto.order.PayOrderRespDTO;
import cn.xiaodong.infra.framework.pay.core.client.dto.refund.PayRefundRespDTO;
import cn.xiaodong.infra.module.pay.controller.admin.notify.vo.PayNotifyTaskDetailRespVO;
import cn.xiaodong.infra.module.pay.controller.admin.notify.vo.PayNotifyTaskPageReqVO;
import cn.xiaodong.infra.module.pay.controller.admin.notify.vo.PayNotifyTaskRespVO;
import cn.xiaodong.infra.module.pay.convert.notify.PayNotifyTaskConvert;
import cn.xiaodong.infra.module.pay.dal.dataobject.app.PayAppDO;
import cn.xiaodong.infra.module.pay.dal.dataobject.notify.PayNotifyLogDO;
import cn.xiaodong.infra.module.pay.dal.dataobject.notify.PayNotifyTaskDO;
import cn.xiaodong.infra.module.pay.service.app.PayAppService;
import cn.xiaodong.infra.module.pay.service.channel.PayChannelService;
import cn.xiaodong.infra.module.pay.service.notify.PayNotifyService;
import cn.xiaodong.infra.module.pay.service.order.PayOrderService;
import cn.xiaodong.infra.module.pay.service.refund.PayRefundService;
import cn.xiaodong.infra.module.pay.enums.ErrorCodeConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

import static cn.xiaodong.infra.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;
import static cn.xiaodong.infra.framework.common.util.collection.CollectionUtils.convertList;

@Tag(name = "管理后台 - 回调通知")
@RestController
@RequestMapping("/pay/notify")
@Validated
@Slf4j
public class PayNotifyController {

    @Resource
    private PayOrderService orderService;
    @Resource
    private PayRefundService refundService;
    @Resource
    private PayNotifyService notifyService;
    @Resource
    private PayAppService appService;
    @Resource
    private PayChannelService channelService;

    @PostMapping(value = "/order/{channelId}")
    @Operation(summary = "支付渠道的统一【支付】回调")
    @PermitAll
    @OperateLog(enable = false) // 回调地址，无需记录操作日志
    public String notifyOrder(@PathVariable("channelId") Long channelId,
                              @RequestParam(required = false) Map<String, String> params,
                              @RequestBody(required = false) String body) {
        log.info("[notifyOrder][channelId({}) 回调数据({}/{})]", channelId, params, body);
        // 1. 校验支付渠道是否存在
        PayClient payClient = channelService.getPayClient(channelId);
        if (payClient == null) {
            log.error("[notifyCallback][渠道编号({}) 找不到对应的支付客户端]", channelId);
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CHANNEL_NOT_FOUND);
        }

        // 2. 解析通知数据
        PayOrderRespDTO notify = payClient.parseOrderNotify(params, body);
        orderService.notifyOrder(channelId, notify);
        return "success";
    }

    @PostMapping(value = "/refund/{channelId}")
    @Operation(summary = "支付渠道的统一【退款】回调")
    @PermitAll
    @OperateLog(enable = false) // 回调地址，无需记录操作日志
    public String notifyRefund(@PathVariable("channelId") Long channelId,
                               @RequestParam(required = false) Map<String, String> params,
                               @RequestBody(required = false) String body) {
        log.info("[notifyRefund][channelId({}) 回调数据({}/{})]", channelId, params, body);
        // 1. 校验支付渠道是否存在
        PayClient payClient = channelService.getPayClient(channelId);
        if (payClient == null) {
            log.error("[notifyCallback][渠道编号({}) 找不到对应的支付客户端]", channelId);
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CHANNEL_NOT_FOUND);
        }

        // 2. 解析通知数据
        PayRefundRespDTO notify = payClient.parseRefundNotify(params, body);
        refundService.notifyRefund(channelId, notify);
        return "success";
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获得回调通知的明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pay:notify:query')")
    public CommonResult<PayNotifyTaskDetailRespVO> getNotifyTaskDetail(@RequestParam("id") Long id) {
        PayNotifyTaskDO task = notifyService.getNotifyTask(id);
        if (task == null) {
            return success(null);
        }
        // 拼接返回
        PayAppDO app = appService.getApp(task.getAppId());
        List<PayNotifyLogDO> logs = notifyService.getNotifyLogList(id);
        return success(PayNotifyTaskConvert.INSTANCE.convert(task, app, logs));
    }

    @GetMapping("/page")
    @Operation(summary = "获得回调通知分页")
    @PreAuthorize("@ss.hasPermission('pay:notify:query')")
    public CommonResult<PageResult<PayNotifyTaskRespVO>> getNotifyTaskPage(@Valid PayNotifyTaskPageReqVO pageVO) {
        PageResult<PayNotifyTaskDO> pageResult = notifyService.getNotifyTaskPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }
        // 拼接返回
        Map<Long, PayAppDO> appMap = appService.getAppMap(convertList(pageResult.getList(), PayNotifyTaskDO::getAppId));
        return success(PayNotifyTaskConvert.INSTANCE.convertPage(pageResult, appMap));
    }

}
