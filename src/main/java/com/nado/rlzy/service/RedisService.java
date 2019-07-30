package com.nado.rlzy.service;

import java.util.concurrent.TimeUnit;

/**
 * redis操作Service,
 * 对象和数组都以json形式进行存储
 * @ClassName redis 操作 service
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/15 17:16
 * @Version 1.0
 */
public interface RedisService {

    /**
     *存储数据
     * @param key
     */
    void set(String key , String code, long time, TimeUnit unit);

    void set(String key, String value);

    /**
     * 获取数据
     * @param key
     * @return
     */
    String get(String key);


    /**
     * 设置超时时间
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);

    boolean hasKey(String key);

    /**
     * 删除数据
     * @param
     */
    void remove(String key);

    Long increment(String key, long delta);



}