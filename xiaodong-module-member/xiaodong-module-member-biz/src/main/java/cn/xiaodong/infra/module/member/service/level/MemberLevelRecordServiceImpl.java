package cn.xiaodong.infra.module.member.service.level;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.module.member.controller.admin.level.vo.record.MemberLevelRecordPageReqVO;
import cn.xiaodong.infra.module.member.dal.dataobject.level.MemberLevelRecordDO;
import cn.xiaodong.infra.module.member.dal.mysql.level.MemberLevelRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 会员等级记录 Service 实现类
 *
 * @author owen
 */
@Service
@Validated
public class MemberLevelRecordServiceImpl implements MemberLevelRecordService {

    @Resource
    private MemberLevelRecordMapper levelLogMapper;

    @Override
    public MemberLevelRecordDO getLevelRecord(Long id) {
        return levelLogMapper.selectById(id);
    }

    @Override
    public PageResult<MemberLevelRecordDO> getLevelRecordPage(MemberLevelRecordPageReqVO pageReqVO) {
        return levelLogMapper.selectPage(pageReqVO);
    }

    @Override
    public void createLevelRecord(MemberLevelRecordDO levelRecord) {
        levelLogMapper.insert(levelRecord);
    }

}
