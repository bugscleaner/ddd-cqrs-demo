package com.cqrs.demo.service;

import com.cqrs.demo.dto.ProductDTO;

import java.util.List;

/**
 * 商品服务实现
 * @Author: yichen
 * @Date: 2022/1/19 8:33 下午
 */
public class ProductServiceImpl implements ProductService{
    @Override
    public void add(ProductDTO productDTO) {

    }

    @Override
    public List<ProductDTO> getByIds(List<Long> itemIds) {
        return null;
    }
}
