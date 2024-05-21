package cn.xiaodong.infra.module.trade.framework.rpc.config;

import cn.xiaodong.infra.module.member.api.address.MemberAddressApi;
import cn.xiaodong.infra.module.member.api.config.MemberConfigApi;
import cn.xiaodong.infra.module.member.api.level.MemberLevelApi;
import cn.xiaodong.infra.module.member.api.point.MemberPointApi;
import cn.xiaodong.infra.module.member.api.user.MemberUserApi;
import cn.xiaodong.infra.module.pay.api.order.PayOrderApi;
import cn.xiaodong.infra.module.pay.api.refund.PayRefundApi;
import cn.xiaodong.infra.module.product.api.category.ProductCategoryApi;
import cn.xiaodong.infra.module.product.api.comment.ProductCommentApi;
import cn.xiaodong.infra.module.product.api.sku.ProductSkuApi;
import cn.xiaodong.infra.module.product.api.spu.ProductSpuApi;
import cn.xiaodong.infra.module.promotion.api.bargain.BargainActivityApi;
import cn.xiaodong.infra.module.promotion.api.bargain.BargainRecordApi;
import cn.xiaodong.infra.module.promotion.api.combination.CombinationRecordApi;
import cn.xiaodong.infra.module.promotion.api.coupon.CouponApi;
import cn.xiaodong.infra.module.promotion.api.discount.DiscountActivityApi;
import cn.xiaodong.infra.module.promotion.api.reward.RewardActivityApi;
import cn.xiaodong.infra.module.promotion.api.seckill.SeckillActivityApi;
import cn.xiaodong.infra.module.system.api.notify.NotifyMessageSendApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {
        BargainActivityApi.class, BargainRecordApi.class, CombinationRecordApi.class,
        CouponApi.class, DiscountActivityApi.class, RewardActivityApi.class, SeckillActivityApi.class,
        MemberUserApi.class, MemberPointApi.class, MemberLevelApi.class, MemberAddressApi.class, MemberConfigApi.class,
        ProductSpuApi.class, ProductSkuApi.class, ProductCommentApi.class, ProductCategoryApi.class,
        PayOrderApi.class, PayRefundApi.class,
        NotifyMessageSendApi.class
})
public class RpcConfiguration {
}
