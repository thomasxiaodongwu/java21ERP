package cn.xiaodong.infra.gateway.filter.security;

import lombok.Data;

import java.util.List;

/**
 * 登录用户信息
 *
 * copy from yudao-spring-boot-starter-security 的 LoginUser 类
 *
 * @author goodWin
 */
@Data
public class LoginUser {

    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 租户编号
     */
    private Long tenantId;
    /**
     * 授权范围
     */
    private List<String> scopes;

}
