package org.example.demo1.dto;

/** 供货供应商信息 + 是否服务购买客户 */
public class SupplierServiceView {

    private Long supplierId;
    private String supplierName;
    /** 是否服务该购买客户 */
    private Boolean servesCustomer;

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

    public Boolean getServesCustomer() {
        return servesCustomer;
    }

    public void setServesCustomer(Boolean servesCustomer) {
        this.servesCustomer = servesCustomer;
    }
}
