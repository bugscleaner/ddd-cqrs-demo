package com.cqrs.demo.repository;

import java.util.List;

/**
 * 数据访问层接口定义
 * @Author: kenny
 * @Date: 2022/1/19 8:42 下午
 */
public interface IRepository<T> {
    /**
     * 新增
     * @param entity
     */
    void save(T entity);

    /**
     * 根据id删除记录
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据id查找记录
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 查找全部
     * @return
     */
    List<T> findAll();

    /**
     * 持久化数据操作
     */
    void flush();
}
