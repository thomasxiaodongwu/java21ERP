package cn.xiaodong.infra.module.pay.service.demo;

import cn.xiaodong.infra.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;

import jakarta.validation.Valid;

/**
 * 示例转账业务 Service 接口
 *
 * @author jason
 */
public interface PayDemoTransferService {

    /**
     * 创建转账单
     *
     * @param userId      用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDemoTransfer(Long userId, @Valid PayDemoTransferCreateReqVO createReqVO);
}
