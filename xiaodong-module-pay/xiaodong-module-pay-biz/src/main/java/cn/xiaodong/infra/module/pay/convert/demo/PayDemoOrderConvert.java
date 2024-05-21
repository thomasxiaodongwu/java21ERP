package cn.xiaodong.infra.module.pay.convert.demo;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.module.pay.controller.admin.demo.vo.PayDemoOrderCreateReqVO;
import cn.xiaodong.infra.module.pay.controller.admin.demo.vo.PayDemoOrderRespVO;
import cn.xiaodong.infra.module.pay.dal.dataobject.demo.PayDemoOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 示例订单 Convert
 *
 * @author goodWin
 */
@Mapper
public interface PayDemoOrderConvert {

    PayDemoOrderConvert INSTANCE = Mappers.getMapper(PayDemoOrderConvert.class);

    PayDemoOrderDO convert(PayDemoOrderCreateReqVO bean);

    PayDemoOrderRespVO convert(PayDemoOrderDO bean);

    PageResult<PayDemoOrderRespVO> convertPage(PageResult<PayDemoOrderDO> page);

}
