package com.cqrs.demo.domain;

/**
 * @Author: yichen
 * @Date: 2022/1/19 8:39 下午
 */
public class ProductDO {
    //自增id
    private  Long id;

    //商品分类Id
    private Long categoryId;

    //商品标题
    private String itemTitle;

    //商品渠道
    private Integer channel;

    //商品渠道
    private Integer status;

    //创建人姓名
    private String createName;

    public ProductDO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}
