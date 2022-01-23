package com.cqrs.demo.repository;

import com.cqrs.demo.domain.ProductSkuDO;
import com.cqrs.demo.domain.ProductSkuDO;

import java.util.List;

/**
 * @Author: yichen
 * @Date: 2022/1/19 8:41 下午
 */
public class ProductSkuRepository extends XMLRepository<ProductSkuDO>{

    public ProductSkuRepository() {
        super("repository/product_sku.xml");
    }

    @Override
    public void save(ProductSkuDO entity) {
        super.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public ProductSkuDO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<ProductSkuDO> findAll() {
        return super.findAll();
    }

    @Override
    public void flush() {
        super.flush();
    }
}
