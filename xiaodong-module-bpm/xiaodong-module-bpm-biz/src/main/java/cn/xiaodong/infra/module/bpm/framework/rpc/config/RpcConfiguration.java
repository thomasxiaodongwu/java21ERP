package cn.xiaodong.infra.module.bpm.framework.rpc.config;

import cn.xiaodong.infra.module.system.api.dept.DeptApi;
import cn.xiaodong.infra.module.system.api.dept.PostApi;
import cn.xiaodong.infra.module.system.api.dict.DictDataApi;
import cn.xiaodong.infra.module.system.api.permission.RoleApi;
import cn.xiaodong.infra.module.system.api.sms.SmsSendApi;
import cn.xiaodong.infra.module.system.api.user.AdminUserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {RoleApi.class, DeptApi.class, PostApi.class, AdminUserApi.class, SmsSendApi.class, DictDataApi.class})
public class RpcConfiguration {
}
