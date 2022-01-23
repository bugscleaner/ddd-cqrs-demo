package com.cqrs.demo.repository;

import com.cqrs.demo.domain.ProductDO;

import java.util.List;

/**
 * @Author: kenny
 * @Date: 2022/1/19 8:41 下午
 */
public class ProductRepository extends XMLRepository<ProductDO>{

    public ProductRepository() {
        super("repository/product.xml");
    }

    @Override
    public void save(ProductDO entity) {
        super.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public ProductDO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<ProductDO> findAll() {
        return super.findAll();
    }

    @Override
    public void flush() {
        super.flush();
    }
}
