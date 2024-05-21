package cn.xiaodong.infra.module.pay.framework.rpc.config;

import cn.xiaodong.infra.module.member.api.user.MemberUserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = MemberUserApi.class)
public class RpcConfiguration {
}
