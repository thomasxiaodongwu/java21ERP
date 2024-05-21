package cn.xiaodong.infra.module.member.enums;

import cn.xiaodong.infra.framework.common.enums.RpcConstants;

/**
 * API 相关的枚举
 *
 * @author goodWin
 */
public class ApiConstants {

    /**
     * 服务名
     *
     * 注意，需要保证和 spring.application.name 保持一致
     */
    public static final String NAME = "member-server";

    public static final String PREFIX = RpcConstants.RPC_API_PREFIX + "/member";

    public static final String VERSION = "1.0.0";

}
