package com.nado.rlzy.base;

import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

/**
 * @ClassName 基础mapper接口
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-06-28 23:22
 * @Version 1.0
 */
public interface BaseMapper<T> {
    /**
     * 添加记录
     *
     * @param entity
     * @return
     * @throws DataAccessException
     */
    Integer save(T entity) throws DataAccessException;

    /**
     * 批量添加记录
     *
     * @param entities
     * @return
     * @throws DataAccessException
     */
    Integer saveBatch(List<T> entities) throws DataAccessException;

    /**
     * 查询单条记录
     *
     * @param id
     * @return
     * @throws DataAccessException
     */
    T queryById(Integer id) throws DataAccessException;


    /**
     * 多条件查询
     *
     * @param query
     * @return
     * @throws DataAccessException
     */
    List<T> queryByParams(T query) throws DataAccessException;


    /**
     * 更新单条记录
     *
     * @param entity
     * @return
     * @throws DataAccessException
     */
    Integer update(T entity) throws DataAccessException;


    /**
     * 批量更新记录
     *
     * @param map
     * @return
     * @throws DataAccessException
     */
    Integer updateBatch(Map map) throws DataAccessException;


    /**
     * 删除单条记录
     *
     * @param id
     * @return
     * @throws DataAccessException
     */
    Integer delete(Integer id) throws DataAccessException;

    /**
     * 批量删除记录
     *
     * @param ids
     * @return
     * @throws DataAccessException
     */
    Integer deleteBatch(Integer[] ids) throws DataAccessException;



}
