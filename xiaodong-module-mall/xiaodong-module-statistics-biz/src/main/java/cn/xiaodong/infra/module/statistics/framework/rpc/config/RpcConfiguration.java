package cn.xiaodong.infra.module.statistics.framework.rpc.config;

import cn.xiaodong.infra.module.product.api.spu.ProductSpuApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {ProductSpuApi.class})
public class RpcConfiguration {
}
