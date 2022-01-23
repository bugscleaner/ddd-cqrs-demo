package com.cqrs.demo.service;

import com.cqrs.demo.domain.ProductDO;
import com.cqrs.demo.dto.ProductDTO;
import com.cqrs.demo.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
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
        ProductRepository productRepository = new ProductRepository();
        ProductDO productDO = new ProductDO();
        productRepository.save(productDO);
        List<ProductDO> doList = productRepository.findAll();
        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductDO ent : doList){
            ProductDTO dto = new ProductDTO();
            dto.setCreateName(ent.getCreateName());
            dto.setItemTitle(ent.getItemTitle());
            dto.setStatus(ent.getStatus());
            dto.setId(ent.getId());
            dto.setCategoryId(ent.getCategoryId());
            dto.setChannel(ent.getChannel());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public static void main(String[] args) {
        ProductServiceImpl productService = new ProductServiceImpl();
        List<ProductDTO> list = productService.getByIds(Arrays.asList(1L, 200L));
        System.out.println(list);
    }
}
