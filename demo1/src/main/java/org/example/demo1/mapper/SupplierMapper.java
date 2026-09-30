package org.example.demo1.mapper;

import org.example.demo1.dto.SupplierProductView;
import org.example.demo1.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 供应商相关查询 */
public interface SupplierMapper {

    /** 全部供应商（前端下拉框） */
    List<Supplier> selectAll();

    /**
     * 查询2：某供应商提供过的所有商品（含未售出）
     * 由 MyBatis resultMap + collection 自动嵌套组装
     */
    List<SupplierProductView> selectSupplierProducts(@Param("supplierId") Long supplierId);
}
