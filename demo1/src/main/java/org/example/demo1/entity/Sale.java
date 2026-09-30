package org.example.demo1.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 销售表 sale：客户与商品一对多。
 * product_id 在数据库上有唯一索引，保证一件商品只能卖给一个客户。
 */
public class Sale {

    private Long id;
    /** 商品ID（唯一） */
    private Long productId;
    /** 购买客户ID */
    private Long customerId;
    /** 购买数量 */
    private Integer quantity;
    /** 销售金额 */
    private BigDecimal amount;
    /** 销售时间 */
    private LocalDateTime saleTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(LocalDateTime saleTime) {
        this.saleTime = saleTime;
    }

    @Override
    public String toString() {
        return "Sale{id=" + id + ", productId=" + productId + ", customerId=" + customerId
                + ", quantity=" + quantity + ", amount=" + amount + ", saleTime=" + saleTime + "}";
    }
}
