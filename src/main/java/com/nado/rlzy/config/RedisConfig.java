package com.nado.rlzy.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @ClassName redis 配置类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/11 9:33
 * @Version 1.0
 */
@Configuration
/**
 * 开启注解
 */
@EnableCaching
public class RedisConfig {

        /**
         * 如使用注解的话需要配置cacheManager
         * @Author lushuaiyu
         * @Description //TODO
         * @Date 9:35 2019/7/11
         * @Param [connectionFactory]
         * @return org.springframework.cache.CacheManager
         **/
        @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
            //初始化一个RedisCacheWriter
            RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
            //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
            //ClassLoader loader = this.getClass().getClassLoader();
            //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
            //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
            //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
            RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
            //设置默认超过期时间是1天
            //defaultCacheConfig.entryTtl(ration.ofDays(1));
            //初始化RedisCacheManager
            RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
            return cacheManager;
    }

    /**
     * 以下两种redisTemplate自由根据场景选择
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:35 2019/7/11
     * @Param [connectionFactory]
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.Object,java.lang.Object>
     **/
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }

   /* public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setBlockWhenExhausted(true);
        config.setTestOnBorrow(true);
        config.setMaxTotal(200);
        config.setMaxIdle(9);
        config.setMinIdle(0);
        config.setMaxWaitMillis(100000);
        return config;
    }*/
}