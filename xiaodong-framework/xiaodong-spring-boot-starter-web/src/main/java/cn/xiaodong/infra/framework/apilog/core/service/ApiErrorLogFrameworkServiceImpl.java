package cn.xiaodong.infra.framework.apilog.core.service;

import cn.hutool.core.bean.BeanUtil;
import cn.xiaodong.infra.module.infra.api.logger.ApiErrorLogApi;
import cn.xiaodong.infra.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

/**
 * API 错误日志 Framework Service 实现类
 *
 * 基于 {@link ApiErrorLogApi} 远程服务，记录错误日志
 *
 * @author goodWin
 */
@RequiredArgsConstructor
public class ApiErrorLogFrameworkServiceImpl implements ApiErrorLogFrameworkService {

    private final ApiErrorLogApi apiErrorLogApi;

    @Override
    @Async
    public void createApiErrorLog(ApiErrorLog apiErrorLog) {
        ApiErrorLogCreateReqDTO reqDTO = BeanUtil.copyProperties(apiErrorLog, ApiErrorLogCreateReqDTO.class);
        apiErrorLogApi.createApiErrorLog(reqDTO).checkError();
    }

}
