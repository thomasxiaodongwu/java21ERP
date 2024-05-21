package cn.xiaodong.infra.module.product.convert.spu;

import cn.xiaodong.infra.framework.common.util.collection.CollectionUtils;
import cn.xiaodong.infra.framework.common.util.object.BeanUtils;
import cn.xiaodong.infra.module.product.controller.admin.spu.vo.ProductSkuRespVO;
import cn.xiaodong.infra.module.product.controller.admin.spu.vo.ProductSpuPageReqVO;
import cn.xiaodong.infra.module.product.controller.admin.spu.vo.ProductSpuRespVO;
import cn.xiaodong.infra.module.product.controller.app.spu.vo.AppProductSpuPageReqVO;
import cn.xiaodong.infra.module.product.dal.dataobject.sku.ProductSkuDO;
import cn.xiaodong.infra.module.product.dal.dataobject.spu.ProductSpuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static cn.xiaodong.infra.framework.common.util.collection.CollectionUtils.convertMultiMap;

/**
 * 商品 SPU Convert
 *
 * @author goodWin
 */
@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    ProductSpuPageReqVO convert(AppProductSpuPageReqVO bean);

    default ProductSpuRespVO convert(ProductSpuDO spu, List<ProductSkuDO> skus) {
        ProductSpuRespVO spuVO = BeanUtils.toBean(spu, ProductSpuRespVO.class);
        spuVO.setSkus(BeanUtils.toBean(skus, ProductSkuRespVO.class));
        return spuVO;
    }

    default List<ProductSpuRespVO> convertForSpuDetailRespListVO(List<ProductSpuDO> spus, List<ProductSkuDO> skus) {
        Map<Long, List<ProductSkuDO>> skuMultiMap = convertMultiMap(skus, ProductSkuDO::getSpuId);
        return CollectionUtils.convertList(spus, spu -> convert(spu, skuMultiMap.get(spu.getId())));
    }

}
