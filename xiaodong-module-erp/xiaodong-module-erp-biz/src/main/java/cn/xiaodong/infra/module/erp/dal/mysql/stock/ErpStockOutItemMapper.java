package cn.xiaodong.infra.module.erp.dal.mysql.stock;

import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.module.erp.dal.dataobject.stock.ErpStockOutItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * ERP 其它出库单项 Mapper
 *
 * @author goodWin
 */
@Mapper
public interface ErpStockOutItemMapper extends BaseMapperX<ErpStockOutItemDO> {

    default List<ErpStockOutItemDO> selectListByOutId(Long outId) {
        return selectList(ErpStockOutItemDO::getOutId, outId);
    }

    default List<ErpStockOutItemDO> selectListByOutIds(Collection<Long> outIds) {
        return selectList(ErpStockOutItemDO::getOutId, outIds);
    }

    default int deleteByOutId(Long outId) {
        return delete(ErpStockOutItemDO::getOutId, outId);
    }

}