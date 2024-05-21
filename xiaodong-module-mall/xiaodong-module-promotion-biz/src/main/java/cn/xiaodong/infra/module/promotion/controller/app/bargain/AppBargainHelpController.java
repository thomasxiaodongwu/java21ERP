package cn.xiaodong.infra.module.promotion.controller.app.bargain;

import cn.hutool.core.collection.CollUtil;
import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.module.member.api.user.MemberUserApi;
import cn.xiaodong.infra.module.member.api.user.dto.MemberUserRespDTO;
import cn.xiaodong.infra.module.promotion.controller.app.bargain.vo.help.AppBargainHelpCreateReqVO;
import cn.xiaodong.infra.module.promotion.controller.app.bargain.vo.help.AppBargainHelpRespVO;
import cn.xiaodong.infra.module.promotion.convert.bargain.BargainHelpConvert;
import cn.xiaodong.infra.module.promotion.dal.dataobject.bargain.BargainHelpDO;
import cn.xiaodong.infra.module.promotion.service.bargain.BargainHelpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;
import static cn.xiaodong.infra.framework.common.util.collection.CollectionUtils.convertSet;
import static cn.xiaodong.infra.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 App - 砍价助力")
@RestController
@RequestMapping("/promotion/bargain-help")
@Validated
public class AppBargainHelpController {

    @Resource
    private BargainHelpService bargainHelpService;

    @Resource
    private MemberUserApi memberUserApi;

    @PostMapping("/create")
    @Operation(summary = "创建砍价助力", description = "给拼团记录砍一刀") // 返回结果为砍价金额，单位：分
    public CommonResult<Integer> createBargainHelp(@RequestBody AppBargainHelpCreateReqVO reqVO) {
        BargainHelpDO help = bargainHelpService.createBargainHelp(getLoginUserId(), reqVO);
        return success(help.getReducePrice());
    }

    @GetMapping("/list")
    @Operation(summary = "获得砍价助力列表")
    @Parameter(name = "recordId", description = "砍价记录编号", required = true, example = "111")
    public CommonResult<List<AppBargainHelpRespVO>> getBargainHelpList(@RequestParam("recordId") Long recordId) {
        List<BargainHelpDO> helps = bargainHelpService.getBargainHelpListByRecordId(recordId);
        if (CollUtil.isEmpty(helps)) {
            return success(Collections.emptyList());
        }
        helps.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime())); // 倒序展示

        // 拼接数据
        Map<Long, MemberUserRespDTO> userMap = memberUserApi.getUserMap(
                convertSet(helps, BargainHelpDO::getUserId));
        return success(BargainHelpConvert.INSTANCE.convertList(helps, userMap));
    }

}
