package cn.xiaodong.infra.module.member.controller.app.address;

import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.framework.security.core.annotations.PreAuthenticated;
import cn.xiaodong.infra.module.member.controller.app.address.vo.AppAddressCreateReqVO;
import cn.xiaodong.infra.module.member.controller.app.address.vo.AppAddressRespVO;
import cn.xiaodong.infra.module.member.controller.app.address.vo.AppAddressUpdateReqVO;
import cn.xiaodong.infra.module.member.convert.address.AddressConvert;
import cn.xiaodong.infra.module.member.dal.dataobject.address.MemberAddressDO;
import cn.xiaodong.infra.module.member.service.address.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.List;

import static cn.xiaodong.infra.framework.common.pojo.CommonResult.success;
import static cn.xiaodong.infra.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 APP - 用户收件地址")
@RestController
@RequestMapping("/member/address")
@Validated
public class AppAddressController {

    @Resource
    private AddressService addressService;

    @PostMapping("/create")
    @Operation(summary = "创建用户收件地址")
    @PreAuthenticated
    public CommonResult<Long> createAddress(@Valid @RequestBody AppAddressCreateReqVO createReqVO) {
        return success(addressService.createAddress(getLoginUserId(), createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户收件地址")
    @PreAuthenticated
    public CommonResult<Boolean> updateAddress(@Valid @RequestBody AppAddressUpdateReqVO updateReqVO) {
        addressService.updateAddress(getLoginUserId(), updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户收件地址")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthenticated
    public CommonResult<Boolean> deleteAddress(@RequestParam("id") Long id) {
        addressService.deleteAddress(getLoginUserId(), id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户收件地址")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthenticated
    public CommonResult<AppAddressRespVO> getAddress(@RequestParam("id") Long id) {
        MemberAddressDO address = addressService.getAddress(getLoginUserId(), id);
        return success(AddressConvert.INSTANCE.convert(address));
    }

    @GetMapping("/get-default")
    @Operation(summary = "获得默认的用户收件地址")
    @PreAuthenticated
    public CommonResult<AppAddressRespVO> getDefaultUserAddress() {
        MemberAddressDO address = addressService.getDefaultUserAddress(getLoginUserId());
        return success(AddressConvert.INSTANCE.convert(address));
    }

    @GetMapping("/list")
    @Operation(summary = "获得用户收件地址列表")
    @PreAuthenticated
    public CommonResult<List<AppAddressRespVO>> getAddressList() {
        List<MemberAddressDO> list = addressService.getAddressList(getLoginUserId());
        return success(AddressConvert.INSTANCE.convertList(list));
    }

}
