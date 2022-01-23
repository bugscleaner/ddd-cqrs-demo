package com.cqrs.demo.domain;

import java.math.BigDecimal;

/**
 * 商品SKU
 * @author : kenny
 * @since : 2022/1/22
 **/
public class ProductSkuDO {
    /**
     * sku id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 价格
     */
    private BigDecimal price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
