package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.Message;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MessageMapper extends Mapper<Message>, MySqlMapper<Message> {
}