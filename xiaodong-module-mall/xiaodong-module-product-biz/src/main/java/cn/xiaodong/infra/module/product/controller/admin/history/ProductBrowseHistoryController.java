package cn.xiaodong.infra.module.product.controller.admin.history;

import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.framework.common.util.object.BeanUtils;
import cn.xiaodong.infra.module.product.controller.admin.history.vo.ProductBrowseHistoryPageReqVO;
import cn.xiaodong.infra.module.product.controller.admin.history.vo.ProductBrowseHistoryRespVO;
import cn.xiaodong.infra.module.product.dal.dataobject.history.ProductBrowseHistoryDO;
import cn.xiaodong.infra.module.product.service.history.ProductBrowseHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 商品浏览记录")
@RestController
@RequestMapping("/product/browse-history")
@Validated
public class ProductBrowseHistoryController {

    @Resource
    private ProductBrowseHistoryService browseHistoryService;

    @GetMapping("/page")
    @Operation(summary = "获得商品浏览记录分页")
    @PreAuthorize("@ss.hasPermission('product:browse-history:query')")
    public CommonResult<PageResult<ProductBrowseHistoryRespVO>> getBrowseHistoryPage(@Valid ProductBrowseHistoryPageReqVO pageReqVO) {
        PageResult<ProductBrowseHistoryDO> pageResult = browseHistoryService.getBrowseHistoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProductBrowseHistoryRespVO.class));
    }

}