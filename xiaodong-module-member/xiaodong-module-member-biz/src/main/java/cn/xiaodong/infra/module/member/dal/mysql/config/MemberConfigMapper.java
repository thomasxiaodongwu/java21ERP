package cn.xiaodong.infra.module.member.dal.mysql.config;

import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.module.member.dal.dataobject.config.MemberConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分设置 Mapper
 *
 * @author QingX
 */
@Mapper
public interface MemberConfigMapper extends BaseMapperX<MemberConfigDO> {
}
