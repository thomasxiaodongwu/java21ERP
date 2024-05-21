package cn.xiaodong.infra.module.infra.dal.mysql.db;

import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.module.infra.dal.dataobject.db.DataSourceConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 * @author goodWin
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapperX<DataSourceConfigDO> {
}
