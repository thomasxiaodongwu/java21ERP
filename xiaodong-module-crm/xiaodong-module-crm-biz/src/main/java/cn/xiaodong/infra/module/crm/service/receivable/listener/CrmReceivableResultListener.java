package cn.xiaodong.infra.module.crm.service.receivable.listener;

import cn.xiaodong.infra.module.bpm.event.BpmProcessInstanceResultEvent;
import cn.xiaodong.infra.module.bpm.event.BpmProcessInstanceResultEventListener;
import cn.xiaodong.infra.module.crm.service.receivable.CrmReceivableService;
import cn.xiaodong.infra.module.crm.service.receivable.CrmReceivableServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 回款审批的结果的监听器实现类
 *
 * @author HUIHUI
 */
@Component
public class CrmReceivableResultListener extends BpmProcessInstanceResultEventListener {

    @Resource
    private CrmReceivableService receivableService;

    @Override
    public String getProcessDefinitionKey() {
        return CrmReceivableServiceImpl.BPM_PROCESS_DEFINITION_KEY;
    }

    @Override
    public void onEvent(BpmProcessInstanceResultEvent event) {
        receivableService.updateReceivableAuditStatus(Long.parseLong(event.getBusinessKey()), event.getResult());
    }

}
