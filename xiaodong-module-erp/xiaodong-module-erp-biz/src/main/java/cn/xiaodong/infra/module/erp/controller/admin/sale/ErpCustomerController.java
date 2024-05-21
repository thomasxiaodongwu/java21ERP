package cn.xiaodong.infra.module.erp.controller.admin.sale;

import cn.xiaodong.infra.framework.common.enums.CommonStatusEnum;
import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.framework.common.pojo.PageParam;
import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.framework.common.util.object.BeanUtils;
import cn.xiaodong.infra.framework.excel.core.util.ExcelUtils;
import cn.xiaodong.infra.framework.operatelog.core.annotations.OperateLog;
import cn.xiaodong.infra.module.erp.controller.admin.sale.vo.customer.ErpCustomerPageReqVO;
import cn.xiaodong.infra.module.erp.controller.admin.sale.vo.customer.ErpCustomerRespVO;
import cn.xiaodong.infra.module.erp.controller.admin.sale.vo.customer.ErpCustomerSaveReqVO;
import cn.xiaodong.infra.module.erp.dal.dataobject.sale.ErpCustomerDO;
import cn.xiaodong.infra.module.erp.service.sale.ErpCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;
import static cn.xiaodong.infra.framework.common.util.collection.CollectionUtils.convertList;
import static cn.xiaodong.infra.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - ERP 客户")
@RestController
@RequestMapping("/erp/customer")
@Validated
public class ErpCustomerController {

    @Resource
    private ErpCustomerService customerService;

    @PostMapping("/create")
    @Operation(summary = "创建客户")
    @PreAuthorize("@ss.hasPermission('erp:customer:create')")
    public CommonResult<Long> createCustomer(@Valid @RequestBody ErpCustomerSaveReqVO createReqVO) {
        return success(customerService.createCustomer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户")
    @PreAuthorize("@ss.hasPermission('erp:customer:update')")
    public CommonResult<Boolean> updateCustomer(@Valid @RequestBody ErpCustomerSaveReqVO updateReqVO) {
        customerService.updateCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('erp:customer:delete')")
    public CommonResult<Boolean> deleteCustomer(@RequestParam("id") Long id) {
        customerService.deleteCustomer(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('erp:customer:query')")
    public CommonResult<ErpCustomerRespVO> getCustomer(@RequestParam("id") Long id) {
        ErpCustomerDO customer = customerService.getCustomer(id);
        return success(BeanUtils.toBean(customer, ErpCustomerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户分页")
    @PreAuthorize("@ss.hasPermission('erp:customer:query')")
    public CommonResult<PageResult<ErpCustomerRespVO>> getCustomerPage(@Valid ErpCustomerPageReqVO pageReqVO) {
        PageResult<ErpCustomerDO> pageResult = customerService.getCustomerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ErpCustomerRespVO.class));
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获得客户精简列表", description = "只包含被开启的客户，主要用于前端的下拉选项")
    public CommonResult<List<ErpCustomerRespVO>> getCustomerSimpleList() {
        List<ErpCustomerDO> list = customerService.getCustomerListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(convertList(list, customer -> new ErpCustomerRespVO().setId(customer.getId()).setName(customer.getName())));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户 Excel")
    @PreAuthorize("@ss.hasPermission('erp:customer:export')")
    @OperateLog(type = EXPORT)
    public void exportCustomerExcel(@Valid ErpCustomerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ErpCustomerDO> list = customerService.getCustomerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户.xls", "数据", ErpCustomerRespVO.class,
                        BeanUtils.toBean(list, ErpCustomerRespVO.class));
    }

}