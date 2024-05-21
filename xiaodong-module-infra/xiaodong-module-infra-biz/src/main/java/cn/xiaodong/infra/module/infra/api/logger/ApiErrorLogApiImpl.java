package cn.xiaodong.infra.module.infra.api.logger;

import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import cn.xiaodong.infra.module.infra.service.logger.ApiErrorLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;

@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class ApiErrorLogApiImpl implements ApiErrorLogApi {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    @Override
    public CommonResult<Boolean> createApiErrorLog(ApiErrorLogCreateReqDTO createDTO) {
        apiErrorLogService.createApiErrorLog(createDTO);
        return success(true);
    }

}
