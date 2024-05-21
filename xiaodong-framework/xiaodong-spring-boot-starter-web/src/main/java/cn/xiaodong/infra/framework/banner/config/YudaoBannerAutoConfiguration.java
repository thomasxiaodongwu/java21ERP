package cn.xiaodong.infra.framework.banner.config;

import cn.xiaodong.infra.framework.banner.core.BannerApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Banner 的自动配置类
 *
 * @author goodWin
 */
@AutoConfiguration
public class YudaoBannerAutoConfiguration {

    @Bean
    public BannerApplicationRunner bannerApplicationRunner() {
        return new BannerApplicationRunner();
    }

}
