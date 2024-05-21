package cn.xiaodong.infra.module.trade.dal.mysql.order;

import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.module.trade.dal.dataobject.order.TradeOrderLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeOrderLogMapper extends BaseMapperX<TradeOrderLogDO> {

    default List<TradeOrderLogDO> selectListByOrderId(Long orderId) {
        return selectList(TradeOrderLogDO::getOrderId, orderId);
    }

}
