package cn.xiaodong.infra.module.pay.convert.wallet;

import cn.xiaodong.infra.module.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeCreateRespVO;
import cn.xiaodong.infra.module.pay.dal.dataobject.wallet.PayWalletRechargeDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletRechargeConvert {

    PayWalletRechargeConvert INSTANCE = Mappers.getMapper(PayWalletRechargeConvert.class);

    @Mapping(target = "totalPrice", expression = "java( payPrice + bonusPrice)")
    PayWalletRechargeDO convert(Long walletId, Integer payPrice, Integer bonusPrice, Long packageId);

    AppPayWalletRechargeCreateRespVO convert(PayWalletRechargeDO bean);

}
