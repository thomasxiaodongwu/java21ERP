package cn.xiaodong.infra.framework.pay.config;

import cn.xiaodong.infra.framework.pay.core.client.PayClientFactory;
import cn.xiaodong.infra.framework.pay.core.client.impl.PayClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 支付配置类
 *
 * @author goodWin
 */
@AutoConfiguration
public class YudaoPayAutoConfiguration {

    @Bean
    public PayClientFactory payClientFactory() {
        return new PayClientFactoryImpl();
    }

}
