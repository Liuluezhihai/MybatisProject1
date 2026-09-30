package org.example.demo1.dto;

import java.util.List;

/** 查询2 最终视图：某供应商 + 其提供过的所有商品（每个商品带购买客户与“是否服务该客户”标记） */
public class SupplierProductView {

    private Long supplierId;
    private String supplierName;
    /** 该供应商提供过的所有商品（含未售出） */
    private List<ProductBuyerView> products;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<ProductBuyerView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBuyerView> products) {
        this.products = products;
    }
}
