package com.cqrs.demo.service;

import com.cqrs.demo.domain.ProductDO;
import com.cqrs.demo.domain.ProductSkuDO;
import com.cqrs.demo.dto.ProductDTO;
import com.cqrs.demo.dto.ProductSkuDTO;
import com.cqrs.demo.repository.ProductRepository;
import com.cqrs.demo.repository.ProductSkuRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品服务实现
 * @Author: kenny
 * @Date: 2022/1/19 8:33 下午
 */
public class ProductServiceImpl implements ProductService{

    /**
     * 新增商品
     * @param productDTO
     */
    @Override
    public void add(ProductDTO productDTO) {
        ProductRepository productRepository = new ProductRepository();
        ProductDO productDO = new ProductDO();
        productDO.setCategoryId(productDTO.getCategoryId());
        productDO.setChannel(productDTO.getChannel());
        productDO.setItemTitle(productDTO.getItemTitle());
        productDO.setStatus(1);

        productRepository.save(productDO);
        productRepository.flush();
    }

    /**
     * 只提供getByIds接口将商品信息透露出去
     * @param itemIds
     * @return
     */
    @Override
    public List<ProductDTO> getByIds(List<Long> itemIds) {
        ProductRepository productRepository = new ProductRepository();
        ProductSkuRepository skuRepository = new ProductSkuRepository();

        List<ProductDO> doList = productRepository.findAll();
        List<ProductSkuDO> skuDOList = skuRepository.findAll();
        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductDO ent : doList){
            ProductDTO dto = new ProductDTO();
            dto.setCreateName(ent.getCreateName());
            dto.setItemTitle(ent.getItemTitle());
            dto.setStatus(ent.getStatus());
            dto.setId(ent.getId());
            dto.setCategoryId(ent.getCategoryId());
            dto.setChannel(ent.getChannel());

            List<ProductSkuDO> skuList = skuDOList.stream().filter(i -> i.getItemId().equals(ent.getId())).collect(Collectors.toList());
            List<ProductSkuDTO> skuDTOList = new ArrayList<>();
            for (ProductSkuDO sku : skuList){
                ProductSkuDTO skuDTO = new ProductSkuDTO();
                skuDTO.setId(sku.getId());
                skuDTO.setItemId(sku.getItemId());
                skuDTO.setPrice(sku.getPrice());
                skuDTOList.add(skuDTO);
            }

            dto.setSkuDTOList(skuDTOList);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
