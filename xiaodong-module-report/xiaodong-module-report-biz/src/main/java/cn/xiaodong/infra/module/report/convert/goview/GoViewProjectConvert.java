package cn.xiaodong.infra.module.report.convert.goview;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.module.report.controller.admin.goview.vo.project.GoViewProjectCreateReqVO;
import cn.xiaodong.infra.module.report.controller.admin.goview.vo.project.GoViewProjectRespVO;
import cn.xiaodong.infra.module.report.controller.admin.goview.vo.project.GoViewProjectUpdateReqVO;
import cn.xiaodong.infra.module.report.dal.dataobject.goview.GoViewProjectDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GoViewProjectConvert {

    GoViewProjectConvert INSTANCE = Mappers.getMapper(GoViewProjectConvert.class);

    GoViewProjectDO convert(GoViewProjectCreateReqVO bean);

    GoViewProjectDO convert(GoViewProjectUpdateReqVO bean);

    GoViewProjectRespVO convert(GoViewProjectDO bean);

    PageResult<GoViewProjectRespVO> convertPage(PageResult<GoViewProjectDO> page);

}
