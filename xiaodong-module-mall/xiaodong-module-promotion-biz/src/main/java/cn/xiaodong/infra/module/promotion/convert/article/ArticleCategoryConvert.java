package cn.xiaodong.infra.module.promotion.convert.article;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.module.promotion.controller.admin.article.vo.category.ArticleCategoryCreateReqVO;
import cn.xiaodong.infra.module.promotion.controller.admin.article.vo.category.ArticleCategoryRespVO;
import cn.xiaodong.infra.module.promotion.controller.admin.article.vo.category.ArticleCategorySimpleRespVO;
import cn.xiaodong.infra.module.promotion.controller.admin.article.vo.category.ArticleCategoryUpdateReqVO;
import cn.xiaodong.infra.module.promotion.controller.app.article.vo.category.AppArticleCategoryRespVO;
import cn.xiaodong.infra.module.promotion.dal.dataobject.article.ArticleCategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章分类 Convert
 *
 * @author HUIHUI
 */
@Mapper
public interface ArticleCategoryConvert {

    ArticleCategoryConvert INSTANCE = Mappers.getMapper(ArticleCategoryConvert.class);

    ArticleCategoryDO convert(ArticleCategoryCreateReqVO bean);

    ArticleCategoryDO convert(ArticleCategoryUpdateReqVO bean);

    ArticleCategoryRespVO convert(ArticleCategoryDO bean);

    List<ArticleCategoryRespVO> convertList(List<ArticleCategoryDO> list);

    PageResult<ArticleCategoryRespVO> convertPage(PageResult<ArticleCategoryDO> page);

    List<ArticleCategorySimpleRespVO> convertList03(List<ArticleCategoryDO> list);

    List<AppArticleCategoryRespVO> convertList04(List<ArticleCategoryDO> categoryList);

}
