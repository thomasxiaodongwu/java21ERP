package cn.xiaodong.infra.module.promotion.framework.rpc.config;

import cn.xiaodong.infra.module.member.api.user.MemberUserApi;
import cn.xiaodong.infra.module.product.api.category.ProductCategoryApi;
import cn.xiaodong.infra.module.product.api.sku.ProductSkuApi;
import cn.xiaodong.infra.module.product.api.spu.ProductSpuApi;
import cn.xiaodong.infra.module.trade.api.order.TradeOrderApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {ProductSkuApi.class, ProductSpuApi.class, ProductCategoryApi.class,
        MemberUserApi.class, TradeOrderApi.class})
public class RpcConfiguration {
}
