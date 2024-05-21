package cn.xiaodong.infra.framework.tenant.config;

import cn.xiaodong.infra.framework.tenant.core.rpc.TenantRequestInterceptor;
import cn.xiaodong.infra.module.system.api.tenant.TenantApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(prefix = "yudao.tenant", value = "enable", matchIfMissing = true)
@EnableFeignClients(clients = TenantApi.class) // 主要是引入相关的 API 服务
public class XiaodongTenantRpcAutoConfiguration {

    @Bean
    public TenantRequestInterceptor tenantRequestInterceptor() {
        return new TenantRequestInterceptor();
    }

}
