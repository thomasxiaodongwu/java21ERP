package cn.xiaodong.infra.module.crm.dal.mysql.business;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.xiaodong.infra.framework.mybatis.core.query.MPJLambdaWrapperX;
import cn.xiaodong.infra.module.crm.controller.admin.business.vo.business.CrmBusinessPageReqVO;
import cn.xiaodong.infra.module.crm.dal.dataobject.business.CrmBusinessDO;
import cn.xiaodong.infra.module.crm.enums.common.CrmBizTypeEnum;
import cn.xiaodong.infra.module.crm.util.CrmPermissionUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
 * 商机 Mapper
 *
 * @author ljlleo
 */
@Mapper
public interface CrmBusinessMapper extends BaseMapperX<CrmBusinessDO> {

    default int updateOwnerUserIdById(Long id, Long ownerUserId) {
        return update(new LambdaUpdateWrapper<CrmBusinessDO>()
                .eq(CrmBusinessDO::getId, id)
                .set(CrmBusinessDO::getOwnerUserId, ownerUserId));
    }

    default PageResult<CrmBusinessDO> selectPageByCustomerId(CrmBusinessPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<CrmBusinessDO>()
                .eq(CrmBusinessDO::getCustomerId, pageReqVO.getCustomerId()) // 指定客户编号
                .likeIfPresent(CrmBusinessDO::getName, pageReqVO.getName())
                .orderByDesc(CrmBusinessDO::getId));
    }

    default PageResult<CrmBusinessDO> selectPageByContactId(CrmBusinessPageReqVO pageReqVO, Collection<Long> businessIds) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<CrmBusinessDO>()
                .in(CrmBusinessDO::getId, businessIds) // 指定商机编号
                .likeIfPresent(CrmBusinessDO::getName, pageReqVO.getName())
                .orderByDesc(CrmBusinessDO::getId));
    }

    default PageResult<CrmBusinessDO> selectPage(CrmBusinessPageReqVO pageReqVO, Long userId) {
        MPJLambdaWrapperX<CrmBusinessDO> query = new MPJLambdaWrapperX<>();
        // 拼接数据权限的查询条件
        CrmPermissionUtils.appendPermissionCondition(query, CrmBizTypeEnum.CRM_BUSINESS.getType(),
                CrmBusinessDO::getId, userId, pageReqVO.getSceneType(), Boolean.FALSE);
        // 拼接自身的查询条件
        query.selectAll(CrmBusinessDO.class)
                .likeIfPresent(CrmBusinessDO::getName, pageReqVO.getName())
                .orderByDesc(CrmBusinessDO::getId);
        return selectJoinPage(pageReqVO, CrmBusinessDO.class, query);
    }

    default Long selectCountByStatusTypeId(Long statusTypeId) {
        return selectCount(CrmBusinessDO::getStatusTypeId, statusTypeId);
    }

}
