package cn.xiaodong.infra.module.member.dal.mysql.signin;

import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.module.member.dal.dataobject.signin.MemberSignInConfigDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 签到规则 Mapper
 *
 * @author QingX
 */
@Mapper
public interface MemberSignInConfigMapper extends BaseMapperX<MemberSignInConfigDO> {

    default MemberSignInConfigDO selectByDay(Integer day) {
        return selectOne(MemberSignInConfigDO::getDay, day);
    }

    default List <MemberSignInConfigDO> selectListByStatus(Integer status) {
        return selectList(MemberSignInConfigDO::getStatus, status);
    }
}
