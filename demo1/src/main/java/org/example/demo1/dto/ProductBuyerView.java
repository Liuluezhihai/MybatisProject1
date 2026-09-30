package org.example.demo1.dto;

import java.math.BigDecimal;

/** 供应商的商品明细 + 购买客户信息 */
public class ProductBuyerView {

    private Long productId;
    private String productName;
    private BigDecimal price;
    /** 是否已售出 */
    private Boolean sold;
    /** 购买客户（未售出时为 null） */
    private Long customerId;
    private String customerName;
    private String saleTime;
    /** 该供应商是否服务该购买客户 */
    private Boolean servesCustomer;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public Boolean getServesCustomer() {
        return servesCustomer;
    }

    public void setServesCustomer(Boolean servesCustomer) {
        this.servesCustomer = servesCustomer;
    }
}
