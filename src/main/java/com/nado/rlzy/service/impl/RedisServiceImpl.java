package com.nado.rlzy.service.impl;

import com.nado.rlzy.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis操作Service的实现类
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/15 17:24
 * @Version 1.0
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void set(String key,  String code, long time, TimeUnit unit ) {
        stringRedisTemplate.opsForValue().set(key,code, time, unit);

    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        return stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }
}