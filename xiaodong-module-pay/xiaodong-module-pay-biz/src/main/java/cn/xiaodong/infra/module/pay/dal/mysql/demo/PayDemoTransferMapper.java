package cn.xiaodong.infra.module.pay.dal.mysql.demo;

import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayDemoTransferMapper extends BaseMapperX<PayDemoTransferDO> {

}
