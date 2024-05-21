package cn.xiaodong.infra.module.infra.dal.mysql.config;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.xiaodong.infra.module.infra.controller.admin.config.vo.ConfigPageReqVO;
import cn.xiaodong.infra.module.infra.dal.dataobject.config.ConfigDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigMapper extends BaseMapperX<ConfigDO> {

    default ConfigDO selectByKey(String key) {
        return selectOne(ConfigDO::getConfigKey, key);
    }

    default PageResult<ConfigDO> selectPage(ConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ConfigDO>()
                .likeIfPresent(ConfigDO::getName, reqVO.getName())
                .likeIfPresent(ConfigDO::getConfigKey, reqVO.getKey())
                .eqIfPresent(ConfigDO::getType, reqVO.getType())
                .betweenIfPresent(ConfigDO::getCreateTime, reqVO.getCreateTime()));
    }

}
