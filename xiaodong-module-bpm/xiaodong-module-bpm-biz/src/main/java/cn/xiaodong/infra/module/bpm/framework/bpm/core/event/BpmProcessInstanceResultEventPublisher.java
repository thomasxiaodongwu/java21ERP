package cn.xiaodong.infra.module.bpm.framework.bpm.core.event;

import cn.xiaodong.infra.module.bpm.event.BpmProcessInstanceResultEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * {@link BpmProcessInstanceResultEvent} 的生产者
 *
 * @author goodWin
 */
@AllArgsConstructor
@Validated
public class BpmProcessInstanceResultEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void sendProcessInstanceResultEvent(@Valid BpmProcessInstanceResultEvent event) {
        publisher.publishEvent(event);
    }

}
