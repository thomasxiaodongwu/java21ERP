package cn.xiaodong.infra.module.pay.convert.wallet;

import cn.xiaodong.infra.framework.common.pojo.PageResult;
import cn.xiaodong.infra.module.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionRespVO;
import cn.xiaodong.infra.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionRespVO;
import cn.xiaodong.infra.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import cn.xiaodong.infra.module.pay.service.wallet.bo.WalletTransactionCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletTransactionConvert {

    PayWalletTransactionConvert INSTANCE = Mappers.getMapper(PayWalletTransactionConvert.class);

    PageResult<AppPayWalletTransactionRespVO> convertPage(PageResult<PayWalletTransactionDO> page);

    PageResult<PayWalletTransactionRespVO> convertPage2(PageResult<PayWalletTransactionDO> page);

    PayWalletTransactionDO convert(WalletTransactionCreateReqBO bean);

}
