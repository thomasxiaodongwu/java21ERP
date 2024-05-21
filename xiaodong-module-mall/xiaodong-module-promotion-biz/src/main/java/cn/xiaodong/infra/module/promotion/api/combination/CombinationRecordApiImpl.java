package cn.xiaodong.infra.module.promotion.api.combination;

import cn.xiaodong.infra.framework.common.exception.util.ServiceExceptionUtil;
import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.module.promotion.api.combination.dto.CombinationRecordCreateReqDTO;
import cn.xiaodong.infra.module.promotion.api.combination.dto.CombinationRecordCreateRespDTO;
import cn.xiaodong.infra.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;
import cn.xiaodong.infra.module.promotion.convert.combination.CombinationActivityConvert;
import cn.xiaodong.infra.module.promotion.dal.dataobject.combination.CombinationRecordDO;
import cn.xiaodong.infra.module.promotion.enums.combination.CombinationRecordStatusEnum;
import cn.xiaodong.infra.module.promotion.service.combination.CombinationRecordService;
import cn.xiaodong.infra.module.promotion.enums.ErrorCodeConstants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import static cn.xiaodong.infra.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;

/**
 * 拼团活动 API 实现类
 *
 * @author HUIHUI
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class CombinationRecordApiImpl implements CombinationRecordApi {

    @Resource
    private CombinationRecordService combinationRecordService;

    @Override
    public CommonResult<Boolean> validateCombinationRecord(Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        combinationRecordService.validateCombinationRecord(userId, activityId, headId, skuId, count);
        return success(true);
    }

    @Override
    public CommonResult<CombinationRecordCreateRespDTO> createCombinationRecord(CombinationRecordCreateReqDTO reqDTO) {
        return success(CombinationActivityConvert.INSTANCE.convert4(combinationRecordService.createCombinationRecord(reqDTO)));
    }

    @Override
    public CommonResult<Boolean> isCombinationRecordSuccess(Long userId, Long orderId) {
        CombinationRecordDO record = combinationRecordService.getCombinationRecord(userId, orderId);
        if (record == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.COMBINATION_RECORD_NOT_EXISTS);
        }
        return success(CombinationRecordStatusEnum.isSuccess(record.getStatus()));
    }

    @Override
    public CommonResult<CombinationValidateJoinRespDTO> validateJoinCombination(
            Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        return success(combinationRecordService.validateJoinCombination(userId, activityId, headId, skuId, count));
    }

}
