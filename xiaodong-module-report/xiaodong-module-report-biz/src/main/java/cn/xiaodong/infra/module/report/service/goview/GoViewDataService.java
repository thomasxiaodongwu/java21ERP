package cn.xiaodong.infra.module.report.service.goview;

import cn.xiaodong.infra.module.report.controller.admin.goview.vo.data.GoViewDataRespVO;

/**
 * GoView 数据 Service 接口
 *
 * @author goodWin
 */
public interface GoViewDataService {

    /**
     * 使用 SQL 查询数据
     *
     * @param sql SQL 语句
     * @return 数据
     */
    GoViewDataRespVO getDataBySQL(String sql);

}
