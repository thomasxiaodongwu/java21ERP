package cn.xiaodong.infra.module.product.api.spu;

import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.framework.common.util.object.BeanUtils;
import cn.xiaodong.infra.module.product.api.spu.dto.ProductSpuRespDTO;
import cn.xiaodong.infra.module.product.dal.dataobject.spu.ProductSpuDO;
import cn.xiaodong.infra.module.product.service.spu.ProductSpuService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;

/**
 * 商品 SPU API 接口实现类
 *
 * @author LeeYan9
 * @since 2022-09-06
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class ProductSpuApiImpl implements ProductSpuApi {

    @Resource
    private ProductSpuService spuService;

    @Override
    public CommonResult<List<ProductSpuRespDTO>> getSpuList(Collection<Long> ids) {
        List<ProductSpuDO> spus = spuService.getSpuList(ids);
        return success(BeanUtils.toBean(spus, ProductSpuRespDTO.class));
    }

    @Override
    public CommonResult<List<ProductSpuRespDTO>> validateSpuList(Collection<Long> ids) {
        List<ProductSpuDO> spus = spuService.validateSpuList(ids);
        return success(BeanUtils.toBean(spus, ProductSpuRespDTO.class));
    }

    @Override
    public CommonResult<ProductSpuRespDTO> getSpu(Long id) {
        ProductSpuDO spu = spuService.getSpu(id);
        return success(BeanUtils.toBean(spu, ProductSpuRespDTO.class));
    }

}
