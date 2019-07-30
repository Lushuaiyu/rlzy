package com.nado.rlzy.db.mapper;

import com.nado.rlzy.db.pojo.ViolationRecord;

public interface ViolationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ViolationRecord record);

    int insertSelective(ViolationRecord record);

    ViolationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ViolationRecord record);

    int updateByPrimaryKey(ViolationRecord record);
}