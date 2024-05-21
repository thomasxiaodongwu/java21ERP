package cn.xiaodong.infra.module.product.api.comment;

import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.module.product.api.comment.dto.ProductCommentCreateReqDTO;
import cn.xiaodong.infra.module.product.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 产品评论")
public interface ProductCommentApi {

    String PREFIX = ApiConstants.PREFIX + "/comment";

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建评论")
    CommonResult<Long> createComment(@RequestBody @Valid ProductCommentCreateReqDTO createReqDTO);

}
