package cn.xiaodong.infra.module.system.framework.rpc.config;

import cn.xiaodong.infra.module.infra.api.file.FileApi;
import cn.xiaodong.infra.module.infra.api.websocket.WebSocketSenderApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {FileApi.class, WebSocketSenderApi.class})
public class RpcConfiguration {
}
