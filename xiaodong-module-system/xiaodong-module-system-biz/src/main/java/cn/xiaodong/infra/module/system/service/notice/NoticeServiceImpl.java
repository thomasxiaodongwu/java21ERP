package cn.xiaodong.infra.module.system.service.notice;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.framework.common.util.object.BeanUtils;
import cn.xiaodong.infra.module.system.controller.admin.notice.vo.NoticePageReqVO;
import cn.xiaodong.infra.module.system.controller.admin.notice.vo.NoticeSaveReqVO;
import cn.xiaodong.infra.module.system.dal.dataobject.notice.NoticeDO;
import cn.xiaodong.infra.module.system.dal.mysql.notice.NoticeMapper;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import static cn.xiaodong.infra.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.xiaodong.infra.module.system.enums.ErrorCodeConstants.NOTICE_NOT_FOUND;

/**
 * 通知公告 Service 实现类
 *
 * @author goodWinCode
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public Long createNotice(NoticeSaveReqVO createReqVO) {
        NoticeDO notice = BeanUtils.toBean(createReqVO, NoticeDO.class);
        noticeMapper.insert(notice);
        return notice.getId();
    }

    @Override
    public void updateNotice(NoticeSaveReqVO updateReqVO) {
        // 校验是否存在
        validateNoticeExists(updateReqVO.getId());
        // 更新通知公告
        NoticeDO updateObj = BeanUtils.toBean(updateReqVO, NoticeDO.class);
        noticeMapper.updateById(updateObj);
    }

    @Override
    public void deleteNotice(Long id) {
        // 校验是否存在
        validateNoticeExists(id);
        // 删除通知公告
        noticeMapper.deleteById(id);
    }

    @Override
    public PageResult<NoticeDO> getNoticePage(NoticePageReqVO reqVO) {
        return noticeMapper.selectPage(reqVO);
    }

    @Override
    public NoticeDO getNotice(Long id) {
        return noticeMapper.selectById(id);
    }

    @VisibleForTesting
    public void validateNoticeExists(Long id) {
        if (id == null) {
            return;
        }
        NoticeDO notice = noticeMapper.selectById(id);
        if (notice == null) {
            throw exception(NOTICE_NOT_FOUND);
        }
    }

}