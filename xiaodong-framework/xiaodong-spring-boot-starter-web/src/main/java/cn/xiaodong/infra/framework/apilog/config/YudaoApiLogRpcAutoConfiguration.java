package cn.xiaodong.infra.framework.apilog.config;

import cn.xiaodong.infra.module.infra.api.logger.ApiAccessLogApi;
import cn.xiaodong.infra.module.infra.api.logger.ApiErrorLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * API 日志使用到 Feign 的配置项
 *
 * @author goodWin
 */
@AutoConfiguration
@EnableFeignClients(clients = {ApiAccessLogApi.class, // 主要是引入相关的 API 服务
        ApiErrorLogApi.class})
public class YudaoApiLogRpcAutoConfiguration {
}
