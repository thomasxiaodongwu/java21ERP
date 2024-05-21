package cn.xiaodong.infra.framework.dict.config;

import cn.xiaodong.infra.framework.dict.core.DictFrameworkUtils;
import cn.xiaodong.infra.module.system.api.dict.DictDataApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class YudaoDictAutoConfiguration {

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public DictFrameworkUtils dictUtils(DictDataApi dictDataApi) {
        DictFrameworkUtils.init(dictDataApi);
        return new DictFrameworkUtils();
    }

}
