package cn.xiaodong.infra.module.product.service.brand;

import cn.xiaodong.infra.framework.common.enums.CommonStatusEnum;
import cn.xiaodong.infra.framework.common.exception.util.ServiceExceptionUtil;
import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.module.product.controller.admin.brand.vo.ProductBrandCreateReqVO;
import cn.xiaodong.infra.module.product.controller.admin.brand.vo.ProductBrandListReqVO;
import cn.xiaodong.infra.module.product.controller.admin.brand.vo.ProductBrandPageReqVO;
import cn.xiaodong.infra.module.product.controller.admin.brand.vo.ProductBrandUpdateReqVO;
import cn.xiaodong.infra.module.product.convert.brand.ProductBrandConvert;
import cn.xiaodong.infra.module.product.dal.dataobject.brand.ProductBrandDO;
import cn.xiaodong.infra.module.product.dal.mysql.brand.ProductBrandMapper;
import cn.xiaodong.infra.module.product.enums.ErrorCodeConstants;
import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

import static cn.xiaodong.infra.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 品牌 Service 实现类
 *
 * @author goodWin
 */
@Service
@Validated
public class ProductBrandServiceImpl implements ProductBrandService {

    @Resource
    private ProductBrandMapper brandMapper;

    @Override
    public Long createBrand(ProductBrandCreateReqVO createReqVO) {
        // 校验
        validateBrandNameUnique(null, createReqVO.getName());

        // 插入
        ProductBrandDO brand = ProductBrandConvert.INSTANCE.convert(createReqVO);
        brandMapper.insert(brand);
        // 返回
        return brand.getId();
    }

    @Override
    public void updateBrand(ProductBrandUpdateReqVO updateReqVO) {
        // 校验存在
        validateBrandExists(updateReqVO.getId());
        validateBrandNameUnique(updateReqVO.getId(), updateReqVO.getName());
        // 更新
        ProductBrandDO updateObj = ProductBrandConvert.INSTANCE.convert(updateReqVO);
        brandMapper.updateById(updateObj);
    }

    @Override
    public void deleteBrand(Long id) {
        // 校验存在
        validateBrandExists(id);
        // 删除
        brandMapper.deleteById(id);
    }

    private void validateBrandExists(Long id) {
        if (brandMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.BRAND_NOT_EXISTS);
        }
    }

    @VisibleForTesting
    public void validateBrandNameUnique(Long id, String name) {
        ProductBrandDO brand = brandMapper.selectByName(name);
        if (brand == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.BRAND_NAME_EXISTS);
        }
        if (!brand.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.BRAND_NAME_EXISTS);
        }
    }

    @Override
    public ProductBrandDO getBrand(Long id) {
        return brandMapper.selectById(id);
    }

    @Override
    public List<ProductBrandDO> getBrandList(Collection<Long> ids) {
        return brandMapper.selectBatchIds(ids);
    }

    @Override
    public List<ProductBrandDO> getBrandList(ProductBrandListReqVO listReqVO) {
        return brandMapper.selectList(listReqVO);
    }

    @Override
    public void validateProductBrand(Long id) {
        ProductBrandDO brand = brandMapper.selectById(id);
        if (brand == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.BRAND_NOT_EXISTS);
        }
        if (brand.getStatus().equals(CommonStatusEnum.DISABLE.getStatus())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.BRAND_DISABLED);
        }
    }

    @Override
    public PageResult<ProductBrandDO> getBrandPage(ProductBrandPageReqVO pageReqVO) {
        return brandMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductBrandDO> getBrandListByStatus(Integer status) {
        return brandMapper.selectListByStatus(status);
    }

}
