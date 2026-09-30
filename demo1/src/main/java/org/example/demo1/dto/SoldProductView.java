package org.example.demo1.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 查询1 最终视图：一件已售商品 + 购买客户 + 全部供货供应商
 * （每个供应商带“是否服务该客户”标记）。
 */
public class SoldProductView {

    private Long productId;
    private String productName;
    private BigDecimal price;
    /** 购买客户 */
    private Long customerId;
    private String customerName;
    private String saleTime;
    /** 供货供应商列表（含是否服务该客户） */
    private List<SupplierServiceView> suppliers;

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

    public List<SupplierServiceView> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierServiceView> suppliers) {
        this.suppliers = suppliers;
    }
}
