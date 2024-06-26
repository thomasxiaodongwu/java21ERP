package cn.xiaodong.infra.module.bpm.convert.definition;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.module.bpm.controller.admin.definition.vo.group.BpmUserGroupCreateReqVO;
import cn.xiaodong.infra.module.bpm.controller.admin.definition.vo.group.BpmUserGroupRespVO;
import cn.xiaodong.infra.module.bpm.controller.admin.definition.vo.group.BpmUserGroupUpdateReqVO;
import cn.xiaodong.infra.module.bpm.dal.dataobject.definition.BpmUserGroupDO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户组 Convert
 *
 * @author goodWin
 */
@Mapper
public interface BpmUserGroupConvert {

    BpmUserGroupConvert INSTANCE = Mappers.getMapper(BpmUserGroupConvert.class);

    BpmUserGroupDO convert(BpmUserGroupCreateReqVO bean);

    BpmUserGroupDO convert(BpmUserGroupUpdateReqVO bean);

    BpmUserGroupRespVO convert(BpmUserGroupDO bean);

    List<BpmUserGroupRespVO> convertList(List<BpmUserGroupDO> list);

    PageResult<BpmUserGroupRespVO> convertPage(PageResult<BpmUserGroupDO> page);

    @Named("convertList2")
    List<BpmUserGroupRespVO> convertList2(List<BpmUserGroupDO> list);

}
