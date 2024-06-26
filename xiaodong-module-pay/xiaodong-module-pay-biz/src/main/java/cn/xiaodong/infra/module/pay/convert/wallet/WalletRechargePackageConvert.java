package cn.xiaodong.infra.module.pay.convert.wallet;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageCreateReqVO;
import cn.xiaodong.infra.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageRespVO;
import cn.xiaodong.infra.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageUpdateReqVO;
import cn.xiaodong.infra.module.pay.dal.dataobject.wallet.PayWalletRechargePackageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WalletRechargePackageConvert {

    WalletRechargePackageConvert INSTANCE = Mappers.getMapper(WalletRechargePackageConvert.class);

    PayWalletRechargePackageDO convert(WalletRechargePackageCreateReqVO bean);

    PayWalletRechargePackageDO convert(WalletRechargePackageUpdateReqVO bean);

    WalletRechargePackageRespVO convert(PayWalletRechargePackageDO bean);

    List<WalletRechargePackageRespVO> convertList(List<PayWalletRechargePackageDO> list);

    PageResult<WalletRechargePackageRespVO> convertPage(PageResult<PayWalletRechargePackageDO> page);

}
