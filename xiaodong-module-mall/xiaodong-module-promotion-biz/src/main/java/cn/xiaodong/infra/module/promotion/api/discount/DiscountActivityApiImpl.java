package cn.xiaodong.infra.module.promotion.api.discount;

import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.module.promotion.api.discount.dto.DiscountProductRespDTO;
import cn.xiaodong.infra.module.promotion.convert.discount.DiscountActivityConvert;
import cn.xiaodong.infra.module.promotion.service.discount.DiscountActivityService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;

/**
 * 限时折扣 API 实现类
 *
 * @author goodWin
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class DiscountActivityApiImpl implements DiscountActivityApi {

    @Resource
    private DiscountActivityService discountActivityService;

    @Override
    public CommonResult<List<DiscountProductRespDTO>> getMatchDiscountProductList(Collection<Long> skuIds) {
        return success(DiscountActivityConvert.INSTANCE.convertList02(discountActivityService.getMatchDiscountProductList(skuIds)));
    }

}
