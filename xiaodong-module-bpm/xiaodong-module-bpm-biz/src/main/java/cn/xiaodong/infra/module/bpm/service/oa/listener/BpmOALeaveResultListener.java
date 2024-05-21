package cn.xiaodong.infra.module.bpm.service.oa.listener;

import cn.xiaodong.infra.module.bpm.event.BpmProcessInstanceResultEvent;
import cn.xiaodong.infra.module.bpm.event.BpmProcessInstanceResultEventListener;
import cn.xiaodong.infra.module.bpm.service.oa.BpmOALeaveService;
import cn.xiaodong.infra.module.bpm.service.oa.BpmOALeaveServiceImpl;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author goodWin
 */
@Component
public class BpmOALeaveResultListener extends BpmProcessInstanceResultEventListener {

    @Resource
    private BpmOALeaveService leaveService;

    @Override
    protected String getProcessDefinitionKey() {
        return BpmOALeaveServiceImpl.PROCESS_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
        leaveService.updateLeaveResult(Long.parseLong(event.getBusinessKey()), event.getResult());
    }

}
