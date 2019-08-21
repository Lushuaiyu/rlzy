package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.Feedback;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lushuaiyu
 */
public interface FeedbackMapper extends Mapper<Feedback> {

    @Select(value = "select content from feedback where delete_flag = 0")
    List<Feedback> feedbck();
}