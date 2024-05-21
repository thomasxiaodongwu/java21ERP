package cn.xiaodong.infra.module.member.framework.rpc.config;

import cn.xiaodong.infra.module.system.api.logger.LoginLogApi;
import cn.xiaodong.infra.module.system.api.sms.SmsCodeApi;
import cn.xiaodong.infra.module.system.api.social.SocialClientApi;
import cn.xiaodong.infra.module.system.api.social.SocialUserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {SmsCodeApi.class, LoginLogApi.class, SocialUserApi.class, SocialClientApi.class})
public class RpcConfiguration {
}
