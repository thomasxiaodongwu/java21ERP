package cn.xiaodong.infra.module.mp.dal.mysql.message;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.framework.mybatis.core.mapper.BaseMapperX;
import cn.xiaodong.infra.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.xiaodong.infra.module.mp.controller.admin.message.vo.message.MpMessagePageReqVO;
import cn.xiaodong.infra.module.mp.dal.dataobject.message.MpMessageDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MpMessageMapper extends BaseMapperX<MpMessageDO> {

    default PageResult<MpMessageDO> selectPage(MpMessagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MpMessageDO>()
                .eqIfPresent(MpMessageDO::getAccountId, reqVO.getAccountId())
                .eqIfPresent(MpMessageDO::getType, reqVO.getType())
                .eqIfPresent(MpMessageDO::getOpenid, reqVO.getOpenid())
                .betweenIfPresent(MpMessageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MpMessageDO::getId));
    }

}
