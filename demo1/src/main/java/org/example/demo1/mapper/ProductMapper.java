package org.example.demo1.mapper;

import org.example.demo1.dto.SoldProductView;

import java.util.List;

/** 商品相关查询 */
public interface ProductMapper {

    /**
     * 查询1：已售商品
     * 一件商品 + 一个购买客户 + 多个供货供应商（含是否服务该客户）
     * 由 MyBatis resultMap + collection 自动嵌套组装
     */
    List<SoldProductView> selectSoldProducts();
}
