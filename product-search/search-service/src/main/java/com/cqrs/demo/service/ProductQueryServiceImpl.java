package com.cqrs.demo.service;

import com.cqrs.demo.dto.ProductDTO;
import demo.dto.PageList;
import demo.dto.ProductQueryDTO;
import demo.service.ProductQueryService;

import java.util.List;

/**
 * @author : kenny
 * @since : 2022/1/23
 **/
public class ProductQueryServiceImpl implements ProductQueryService {

    @Override
    public PageList<List<ProductDTO>> pageList(ProductQueryDTO queryParam) {



        return null;
    }
}
