package cn.xiaodong.infra.module.trade.dal.mysql.config;

import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.module.trade.dal.dataobject.config.TradeConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 交易中心配置 Mapper
 *
 * @author owen
 */
@Mapper
public interface TradeConfigMapper extends BaseMapperX<TradeConfigDO> {

}
