package com.nado.rlzy.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper 接口, 继承通用mapper 接口
 * Mapper接口：基本的增、删、改、查方法
 * MySqlMapper：针对MySQL的额外补充接口，支持批量插入
 *
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/5/22 9:37
 * @Version 1.0
 */
public interface TKMapper<T> extends Mapper<T>, MySqlMapper<T> {
}