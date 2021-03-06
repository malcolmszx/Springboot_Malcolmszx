package com.kingdee.abc.annotation;

import org.springframework.context.annotation.Import;

import com.kingdee.abc.config.RedissonAutoConfiguration;

import java.lang.annotation.*;

/**
 * @author malcolmszx
 * @date 2018/7/10
 * @desc 开启Redisson注解支持
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RedissonAutoConfiguration.class)
public @interface EnableRedissonLock {
}
