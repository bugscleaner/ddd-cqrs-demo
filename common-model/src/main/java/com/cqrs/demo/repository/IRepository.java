package com.cqrs.demo.repository;

/**
 * 数据访问层接口定义
 * @Author: yichen
 * @Date: 2022/1/19 8:42 下午
 */
public interface IRepository<T> {
    void save(T entity);

    void deleteById(Long id);

    T findById(Long id);
}
