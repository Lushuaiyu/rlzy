package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.dto.ComplaintPage;
import com.nado.rlzy.db.pojo.HrDictionaryItem;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HrDictionaryItemMapper extends Mapper<HrDictionaryItem> {
    @Select(value = "select name as dictionaryName, value as dictionaryValue from hr_dictionary_item where pId = #{typeId}")
     List<ComplaintPage> dictionary(Integer typeId);
}