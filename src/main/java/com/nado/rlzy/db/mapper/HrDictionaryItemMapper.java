package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.query.DictionaryQuery;
import com.nado.rlzy.db.pojo.HrDictionaryItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HrDictionaryItemMapper extends Mapper<HrDictionaryItem> {
    @Select(value = "select name as dictionaryName, value as dictionaryValue from hr_dictionary_item where pId = #{typeId}")
    <T>
     List<T> dictionary(Integer typeId);


    /**
     * 发布简章时的前端选项内容
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 17:52 2019-09-08
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrDictionaryItem>
     */
    List<HrDictionaryItem> selectFrontEndOption(DictionaryQuery query);
    List<HrDictionaryItem> selectFrontEndOption2(DictionaryQuery query);
    List<HrDictionaryItem> selectFrontEndOption3(DictionaryQuery query);
    List<HrDictionaryItem> selectFrontEndOption4(DictionaryQuery query);
    List<HrDictionaryItem> selectFrontEndOption5(DictionaryQuery query);
    List<HrDictionaryItem> selectFrontEndOption6(DictionaryQuery query);
    List<HrDictionaryItem> selectFrontEndOption7(DictionaryQuery query);
    List<HrDictionaryItem> selectFrontEndOption8(DictionaryQuery query);
    List<HrDictionaryItem> selectFrontEndOption9(DictionaryQuery query);
    List<HrDictionaryItem> selectFrontEndOption10(DictionaryQuery query);
    List<HrDictionaryItem> selectFrontEndOption11(DictionaryQuery query);


}