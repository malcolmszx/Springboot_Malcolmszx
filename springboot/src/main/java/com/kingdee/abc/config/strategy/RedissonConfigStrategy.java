package com.kingdee.abc.config.strategy;

import org.redisson.config.Config;

/**
 * @author malcolmszx
 * @date 2018/7/12
 * @desc Redisson配置构建接口
 */
public interface RedissonConfigStrategy {

    /**
     * 根据不同的Redis配置策略创建对应的Config
     * @param address
     * @return Config
     */
    Config createRedissonConfig(String address);
}
