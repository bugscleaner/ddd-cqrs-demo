package com.cqrs.demo.service;

import com.cqrs.demo.dto.ProductDTO;

import java.util.List;

/**
 * 商品服务 - 对外暴露
 * @Author: yichen
 * @Date: 2022/1/19 8:31
 */
public interface ProductService {

    /**
     * 新增商品
     */
    void add(ProductDTO productDTO);

    /**
     * 批量获取商品信息
     * @param itemIds
     * @return
     */
    List<ProductDTO> getByIds(List<Long> itemIds);
}
