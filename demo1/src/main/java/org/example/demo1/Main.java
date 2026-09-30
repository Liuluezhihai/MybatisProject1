package org.example.demo1;

import org.example.demo1.dto.ProductBuyerView;
import org.example.demo1.dto.SoldProductView;
import org.example.demo1.dto.SupplierProductView;
import org.example.demo1.dto.SupplierServiceView;
import org.example.demo1.entity.Supplier;
import org.example.demo1.service.SaleQueryService;

import java.util.List;

/**
 * 控制台入口：
 *   查询1 - 已售商品 → 购买客户 + 供货供应商（含是否服务该客户）
 *   查询2 - 某供应商提供过的所有商品 → 购买客户（含是否被该供应商服务）
 */
public class Main {

    private static final SaleQueryService SERVICE = new SaleQueryService();

    public static void main(String[] args) {
        System.out.println("=".repeat(72));
        System.out.println("  MyBatis 作业控制台输出（数据库 mybatislesson）");
        System.out.println("=".repeat(72));

        printQuery1();
        printQuery2();
    }

    // ---------- 查询1 ----------
    private static void printQuery1() {
        System.out.println("\n>>> 查询 1：已经销售出去的商品 <<<");
        System.out.println("-" .repeat(72));

        List<SoldProductView> soldProducts = SERVICE.listSoldProducts();
        if (soldProducts.isEmpty()) {
            System.out.println("（无已售商品）");
            return;
        }

        for (SoldProductView v : soldProducts) {
            System.out.println();
            System.out.println("商品 [ID=" + v.getProductId() + "] " + v.getProductName()
                    + "  单价 ￥" + v.getPrice());
            System.out.println("  购买客户：" + v.getCustomerName()
                    + " (ID=" + v.getCustomerId() + ")  购买时间：" + v.getSaleTime());
            System.out.println("  供货供应商（" + v.getSuppliers().size() + " 家）：");
            for (SupplierServiceView s : v.getSuppliers()) {
                String tag = Boolean.TRUE.equals(s.getServesCustomer()) ? "★ 服务该客户" : "✗ 未服务该客户";
                System.out.println("    - [ID=" + s.getSupplierId() + "] " + s.getSupplierName() + "  →  " + tag);
            }
        }
    }

    // ---------- 查询2：对每个供应商都跑一遍 ----------
    private static void printQuery2() {
        System.out.println("\n>>> 查询 2：各供应商提供过的所有商品 <<<");
        System.out.println("-" .repeat(72));

        List<Supplier> suppliers = SERVICE.listSuppliers();
        for (Supplier sup : suppliers) {
            SupplierProductView view = SERVICE.listSupplierProducts(sup.getId());
            System.out.println();
            if (view == null || view.getProducts() == null) {
                System.out.println("供应商 [ID=" + sup.getId() + "] " + sup.getName() + " ：无商品");
                continue;
            }
            System.out.println("供应商 [ID=" + view.getSupplierId() + "] " + view.getSupplierName()
                    + "  （共提供 " + view.getProducts().size() + " 种商品）");
            for (ProductBuyerView p : view.getProducts()) {
                System.out.print("    商品 [ID=" + p.getProductId() + "] " + p.getProductName()
                        + "  ￥" + p.getPrice());
                if (Boolean.TRUE.equals(p.getSold())) {
                    String tag = Boolean.TRUE.equals(p.getServesCustomer())
                            ? "★ 服务该客户" : "✗ 未服务该客户";
                    System.out.println("  → 已售出 购买客户：" + p.getCustomerName()
                            + " (ID=" + p.getCustomerId() + ")  " + tag + "  时间：" + p.getSaleTime());
                } else {
                    System.out.println("  → 未售出");
                }
            }
        }
    }
}
