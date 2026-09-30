package org.example.demo1.service;

import org.apache.ibatis.session.SqlSession;
import org.example.demo1.dto.SoldProductView;
import org.example.demo1.dto.SupplierProductView;
import org.example.demo1.entity.Supplier;
import org.example.demo1.mapper.ProductMapper;
import org.example.demo1.mapper.SupplierMapper;
import org.example.demo1.util.MyBatisUtil;

import java.util.List;

/** 销售/供应商查询服务：委托 Mapper，嵌套组装由 MyBatis resultMap + collection 完成 */
public class SaleQueryService {

    /**
     * 查询1：已售商品 -> 购买客户 + 供货供应商（含是否服务该客户）
     */
    public List<SoldProductView> listSoldProducts() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            return session.getMapper(ProductMapper.class).selectSoldProducts();
        }
    }

    /**
     * 查询2：某供应商提供过的所有商品 -> 购买客户（含是否被该供应商服务）
     */
    public SupplierProductView listSupplierProducts(Long supplierId) {
        try (SqlSession session = MyBatisUtil.openSession()) {
            List<SupplierProductView> list =
                    session.getMapper(SupplierMapper.class).selectSupplierProducts(supplierId);
            return list.isEmpty() ? null : list.get(0);
        }
    }

    /** 全部供应商（前端下拉框） */
    public List<Supplier> listSuppliers() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            return session.getMapper(SupplierMapper.class).selectAll();
        }
    }
}
