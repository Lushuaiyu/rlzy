package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.dto.ProvinceDto;
import com.nado.rlzy.db.pojo.Province;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lushuaiyu
 */
@Repository
public interface ProvinceMapper {
    /**
     * 省市区
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.ProvinceDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:31 2019/6/28
     * @Param []
     **/
    List<Province> getPCA();

}