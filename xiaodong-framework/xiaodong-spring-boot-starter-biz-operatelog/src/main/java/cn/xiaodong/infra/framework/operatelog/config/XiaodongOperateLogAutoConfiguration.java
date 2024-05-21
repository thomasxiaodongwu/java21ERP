package cn.xiaodong.infra.framework.operatelog.config;

import cn.xiaodong.infra.framework.operatelog.core.aop.OperateLogAspect;
import cn.xiaodong.infra.framework.operatelog.core.service.OperateLogFrameworkService;
import cn.xiaodong.infra.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import cn.xiaodong.infra.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class XiaodongOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
