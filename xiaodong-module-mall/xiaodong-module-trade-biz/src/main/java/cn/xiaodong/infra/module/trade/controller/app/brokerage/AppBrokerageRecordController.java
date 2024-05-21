package cn.xiaodong.infra.module.trade.controller.app.brokerage;

import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.framework.common.util.object.BeanUtils;
import cn.xiaodong.infra.framework.security.core.annotations.PreAuthenticated;
import cn.xiaodong.infra.module.trade.controller.app.brokerage.vo.record.AppBrokerageProductPriceRespVO;
import cn.xiaodong.infra.module.trade.controller.app.brokerage.vo.record.AppBrokerageRecordPageReqVO;
import cn.xiaodong.infra.module.trade.controller.app.brokerage.vo.record.AppBrokerageRecordRespVO;
import cn.xiaodong.infra.module.trade.convert.brokerage.BrokerageRecordConvert;
import cn.xiaodong.infra.module.trade.dal.dataobject.brokerage.BrokerageRecordDO;
import cn.xiaodong.infra.module.trade.service.brokerage.BrokerageRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;
import static cn.xiaodong.infra.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

@Tag(name = "用户 APP - 分销用户")
@RestController
@RequestMapping("/trade/brokerage-record")
@Validated
@Slf4j
public class AppBrokerageRecordController {
    @Resource
    private BrokerageRecordService brokerageRecordService;

    @GetMapping("/page")
    @Operation(summary = "获得分销记录分页")
    @PreAuthenticated
    public CommonResult<PageResult<AppBrokerageRecordRespVO>> getBrokerageRecordPage(@Valid AppBrokerageRecordPageReqVO pageReqVO) {
        PageResult<BrokerageRecordDO> pageResult = brokerageRecordService.getBrokerageRecordPage(
                BrokerageRecordConvert.INSTANCE.convert(pageReqVO, getLoginUserId()));
        return success(BeanUtils.toBean(pageResult, AppBrokerageRecordRespVO.class));
    }

    @GetMapping("/get-product-brokerage-price")
    @Operation(summary = "获得商品的分销金额")
    public CommonResult<AppBrokerageProductPriceRespVO> getProductBrokeragePrice(@RequestParam("spuId") Long spuId) {
        return success(brokerageRecordService.calculateProductBrokeragePrice(getLoginUserId(), spuId));
    }

}