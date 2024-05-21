package cn.xiaodong.infra.framework.tenant.core.service;

import cn.xiaodong.infra.framework.common.pojo.CommonResult;
import cn.xiaodong.infra.framework.common.util.cache.CacheUtils;
import cn.xiaodong.infra.module.system.api.tenant.TenantApi;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.time.Duration;
import java.util.List;

import static cn.xiaodong.infra.framework.common.util.cache.CacheUtils.buildAsyncReloadingCache;

/**
 * Tenant 框架 Service 实现类
 *
 * @author goodWin
 */
@RequiredArgsConstructor
public class TenantFrameworkServiceImpl implements TenantFrameworkService {

    private final TenantApi tenantApi;

    /**
     * 针对 {@link #getTenantIds()} 的缓存
     */
    private final LoadingCache<Object, List<Long>> getTenantIdsCache = buildAsyncReloadingCache(
            Duration.ofMinutes(1L), // 过期时间 1 分钟
            new CacheLoader<Object, List<Long>>() {

                @Override
                public List<Long> load(Object key) {
                    return tenantApi.getTenantIdList().getCheckedData();
                }

            });

    /**
     * 针对 {@link #validTenant(Long)} 的缓存
     */
    private final LoadingCache<Long, CommonResult<Boolean>> validTenantCache = buildAsyncReloadingCache(
            Duration.ofMinutes(1L), // 过期时间 1 分钟
            new CacheLoader<Long, CommonResult<Boolean>>() {

                @Override
                public CommonResult<Boolean> load(Long id) {
                    return tenantApi.validTenant(id);
                }

            });

    @Override
    @SneakyThrows
    public List<Long> getTenantIds() {
        return getTenantIdsCache.get(Boolean.TRUE);
    }

    @Override
    @SneakyThrows
    public void validTenant(Long id) {
        validTenantCache.get(id).checkError();
    }

}
