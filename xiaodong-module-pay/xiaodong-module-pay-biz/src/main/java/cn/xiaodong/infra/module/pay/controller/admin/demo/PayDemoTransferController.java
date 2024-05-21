package cn.xiaodong.infra.module.pay.controller.admin.demo;

import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import cn.xiaodong.infra.module.pay.service.demo.PayDemoTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;
import static cn.xiaodong.infra.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 示例转账单")
@RestController
@RequestMapping("/pay/demo-transfer")
@Validated
public class PayDemoTransferController {
    @Resource
    private PayDemoTransferService demoTransferService;

    @PostMapping("/create")
    @Operation(summary = "创建示例转账订单")
    public CommonResult<Long> createDemoOrder(@Valid @RequestBody PayDemoTransferCreateReqVO createReqVO) {
        return success(demoTransferService.createDemoTransfer(getLoginUserId(), createReqVO));
    }
}
