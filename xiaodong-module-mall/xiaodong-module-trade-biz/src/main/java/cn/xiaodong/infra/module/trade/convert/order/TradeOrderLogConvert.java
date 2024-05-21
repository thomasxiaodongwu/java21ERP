package cn.xiaodong.infra.module.trade.convert.order;

import cn.xiaodong.infra.module.trade.dal.dataobject.order.TradeOrderLogDO;
import cn.xiaodong.infra.module.trade.service.order.bo.TradeOrderLogCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TradeOrderLogConvert {

    TradeOrderLogConvert INSTANCE = Mappers.getMapper(TradeOrderLogConvert.class);

    TradeOrderLogDO convert(TradeOrderLogCreateReqBO bean);

}
