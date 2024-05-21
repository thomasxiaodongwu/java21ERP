package cn.xiaodong.infra.module.promotion.api.coupon;


import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.module.promotion.api.coupon.dto.CouponRespDTO;
import cn.xiaodong.infra.module.promotion.api.coupon.dto.CouponUseReqDTO;
import cn.xiaodong.infra.module.promotion.api.coupon.dto.CouponValidReqDTO;
import cn.xiaodong.infra.module.promotion.convert.coupon.CouponConvert;
import cn.xiaodong.infra.module.promotion.dal.dataobject.coupon.CouponDO;
import cn.xiaodong.infra.module.promotion.service.coupon.CouponService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;

/**
 * 优惠劵 API 实现类
 *
 * @author goodWin
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class CouponApiImpl implements CouponApi {

    @Resource
    private CouponService couponService;

    @Override
    public CommonResult<Boolean> useCoupon(CouponUseReqDTO useReqDTO) {
        couponService.useCoupon(useReqDTO.getId(), useReqDTO.getUserId(), useReqDTO.getOrderId());
        return success(true);
    }

    @Override
    public CommonResult<Boolean> returnUsedCoupon(Long id) {
        couponService.returnUsedCoupon(id);
        return success(true);
    }

    @Override
    public CommonResult<CouponRespDTO> validateCoupon(CouponValidReqDTO validReqDTO) {
        CouponDO coupon = couponService.validCoupon(validReqDTO.getId(), validReqDTO.getUserId());
        return success(CouponConvert.INSTANCE.convert(coupon));
    }

}
