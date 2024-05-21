package cn.xiaodong.infra.module.infra.framework.file.config;

import cn.xiaodong.infra.module.infra.framework.file.core.client.FileClientFactory;
import cn.xiaodong.infra.module.infra.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件配置类
 *
 * @author goodWin
 */
@Configuration(proxyBeanMethods = false)
public class YudaoFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
