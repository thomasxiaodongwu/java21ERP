package cn.xiaodong.infra.module.product.api.category;

import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.module.product.service.category.ProductCategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Collection;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;

/**
 * 商品分类 API 接口实现类
 *
 * @author owen
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class ProductCategoryApiImpl implements ProductCategoryApi {

    @Resource
    private ProductCategoryService productCategoryService;

    @Override
    public CommonResult<Boolean> validateCategoryList(Collection<Long> ids) {
        productCategoryService.validateCategoryList(ids);
        return success(true);
    }

}
