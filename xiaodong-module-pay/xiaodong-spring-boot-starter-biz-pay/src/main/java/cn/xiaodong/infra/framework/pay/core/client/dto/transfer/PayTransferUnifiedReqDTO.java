package cn.xiaodong.infra.framework.pay.core.client.dto.transfer;

import cn.xiaodong.infra.framework.common.validation.InEnum;
import cn.xiaodong.infra.framework.pay.core.enums.transfer.PayTransferTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

/**
 * 统一转账 Request DTO
 *
 * @author jason
 */
@Data
public class PayTransferUnifiedReqDTO {

    /**
     * 转账类型
     *
     * 关联 {@link PayTransferTypeEnum#getType()}
     */
    @NotNull(message = "转账类型不能为空")
    @InEnum(PayTransferTypeEnum.class)
    private Integer type;

    /**
     * 用户 IP
     */
    @NotEmpty(message = "用户 IP 不能为空")
    private String userIp;

    @NotEmpty(message = "外部转账单编号不能为空")
    private String outTransferNo;

    /**
     * 转账金额，单位：分
     */
    @NotNull(message = "转账金额不能为空")
    @Min(value = 1, message = "转账金额必须大于零")
    private Integer price;

    /**
     * 转账标题
     */
    @NotEmpty(message = "转账标题不能为空")
    @Length(max = 128, message = "转账标题不能超过 128")
    private String title;

    /**
     * 收款方信息。
     *
     * 转账类型 {@link #type} 不同，收款方信息不同
     */
    @NotEmpty(message = "收款方信息 不能为空")
    private Map<String, String> payeeInfo;

    /**
     * 支付渠道的额外参数
     */
    private Map<String, String> channelExtras;

}
