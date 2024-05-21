package cn.xiaodong.infra.module.trade.convert.aftersale;

import cn.xiaodong.infra.module.trade.dal.dataobject.aftersale.AfterSaleLogDO;
import cn.xiaodong.infra.module.trade.service.aftersale.bo.AfterSaleLogCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AfterSaleLogConvert {

    AfterSaleLogConvert INSTANCE = Mappers.getMapper(AfterSaleLogConvert.class);

    AfterSaleLogDO convert(AfterSaleLogCreateReqBO bean);

}
