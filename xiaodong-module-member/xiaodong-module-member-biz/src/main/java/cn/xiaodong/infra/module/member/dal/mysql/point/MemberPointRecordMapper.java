package cn.xiaodong.infra.module.member.dal.mysql.point;

import cn.xiaodong.infra.framework.common.pojo.PageParam;
import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.xiaodong.infra.module.member.controller.admin.point.vo.recrod.MemberPointRecordPageReqVO;
import cn.xiaodong.infra.module.member.controller.app.point.vo.AppMemberPointRecordPageReqVO;
import cn.xiaodong.infra.module.member.dal.dataobject.point.MemberPointRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 用户积分记录 Mapper
 *
 * @author QingX
 */
@Mapper
public interface MemberPointRecordMapper extends BaseMapperX<MemberPointRecordDO> {

    default PageResult<MemberPointRecordDO> selectPage(MemberPointRecordPageReqVO reqVO, Set<Long> userIds) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberPointRecordDO>()
                .inIfPresent(MemberPointRecordDO::getUserId, userIds)
                .eqIfPresent(MemberPointRecordDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MemberPointRecordDO::getBizType, reqVO.getBizType())
                .likeIfPresent(MemberPointRecordDO::getTitle, reqVO.getTitle())
                .orderByDesc(MemberPointRecordDO::getId));
    }

    default PageResult<MemberPointRecordDO> selectPage(Long userId, AppMemberPointRecordPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<MemberPointRecordDO>()
                .eq(MemberPointRecordDO::getUserId, userId)
                .betweenIfPresent(MemberPointRecordDO::getCreateTime, pageReqVO.getCreateTime())
                .gt(Boolean.TRUE.equals(pageReqVO.getAddStatus()),
                        MemberPointRecordDO::getPoint, 0)
                .lt(Boolean.FALSE.equals(pageReqVO.getAddStatus()),
                        MemberPointRecordDO::getPoint, 0)
                .orderByDesc(MemberPointRecordDO::getId));
    }

}
