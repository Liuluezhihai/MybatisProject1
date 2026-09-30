-- ============================================================
-- 作业数据库：mybatislesson
-- 业务实体：商品(product)、客户(customer)、供应商(supplier)
-- 实体关系：
--   1. 供应商 - 商品   多对多 -> supplier_product（供应商-商品中间表）
--   2. 客户   - 商品   一对多 -> sale（销售表，product_id 加唯一索引，
--                                保证同一件商品只能卖给一个客户）
--   3. 供应商 - 客户   多对多 -> supplier_customer（供应商-客户中间表）
-- ============================================================

DROP DATABASE IF EXISTS mybatislesson;
CREATE DATABASE mybatislesson DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE mybatislesson;

-- ------------------------------------------------------------
-- 1. 基础表
-- ------------------------------------------------------------

-- 商品表
CREATE TABLE product (
    id    BIGINT AUTO_INCREMENT COMMENT '商品ID' PRIMARY KEY,
    name  VARCHAR(100) NOT NULL COMMENT '商品名称',
    price DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '单价',
    stock INT NOT NULL DEFAULT 0 COMMENT '库存'
) COMMENT '商品表';

-- 客户表
CREATE TABLE customer (
    id    BIGINT AUTO_INCREMENT COMMENT '客户ID' PRIMARY KEY,
    name  VARCHAR(100) NOT NULL COMMENT '客户姓名',
    phone VARCHAR(20) NULL COMMENT '联系电话'
) COMMENT '客户表';

-- 供应商表
CREATE TABLE supplier (
    id      BIGINT AUTO_INCREMENT COMMENT '供应商ID' PRIMARY KEY,
    name    VARCHAR(100) NOT NULL COMMENT '供应商名称',
    contact VARCHAR(100) NULL COMMENT '联系方式'
) COMMENT '供应商表';

-- ------------------------------------------------------------
-- 2. 中间关联表
-- ------------------------------------------------------------

-- 供应商-商品中间表（多对多）
CREATE TABLE supplier_product (
    supplier_id BIGINT NOT NULL COMMENT '供应商ID',
    product_id  BIGINT NOT NULL COMMENT '商品ID',
    PRIMARY KEY (supplier_id, product_id),
    CONSTRAINT fk_sp_supplier FOREIGN KEY (supplier_id) REFERENCES supplier (id),
    CONSTRAINT fk_sp_product FOREIGN KEY (product_id) REFERENCES product (id)
) COMMENT '供应商-商品中间表(多对多)';

-- 供应商-客户中间表（多对多）
CREATE TABLE supplier_customer (
    supplier_id BIGINT NOT NULL COMMENT '供应商ID',
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    PRIMARY KEY (supplier_id, customer_id),
    CONSTRAINT fk_sc_supplier FOREIGN KEY (supplier_id) REFERENCES supplier (id),
    CONSTRAINT fk_sc_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
) COMMENT '供应商-客户中间表(多对多)';

-- 销售表：客户与商品一对多（一个客户可买多个商品）
-- product_id 唯一索引保证：同一件商品售出后不能再卖给别的客户
CREATE TABLE sale (
    id          BIGINT AUTO_INCREMENT COMMENT '销售记录ID' PRIMARY KEY,
    product_id  BIGINT NOT NULL COMMENT '商品ID(唯一，一件商品只能卖给一个客户)',
    customer_id BIGINT NOT NULL COMMENT '购买客户ID',
    quantity    INT NOT NULL DEFAULT 1 COMMENT '购买数量',
    amount      DECIMAL(12, 2) NULL COMMENT '销售金额',
    sale_time   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '销售时间',
    UNIQUE KEY uk_sale_product (product_id),
    CONSTRAINT fk_sale_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_sale_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
) COMMENT '销售表(客户-商品一对多)';

-- ------------------------------------------------------------
-- 3. 测试数据
-- ------------------------------------------------------------

INSERT INTO product (name, price, stock) VALUES
('机械键盘', 299.00, 50),
('无线鼠标', 129.00, 120),
('显示器支架', 89.00, 80),
('USB-C 扩展坞', 199.00, 60);

INSERT INTO supplier (name, contact) VALUES
('华东供应商', '13800000001'),
('华南供应商', '13800000002'),
('华北供应商', '13800000003');

INSERT INTO customer (name, phone) VALUES
('张伟', '13900000001'),
('李娜', '13900000002'),
('王强', '13900000003');

-- 供应商-商品：S1 提供 P1、P2；S2 提供 P2、P3；S3 提供 P4
INSERT INTO supplier_product (supplier_id, product_id) VALUES
(1, 1), (1, 2), (2, 2), (2, 3), (3, 4);

-- 供应商-客户：S1 服务 张伟、李娜；S2 服务 李娜；S3 服务 王强
INSERT INTO supplier_customer (supplier_id, customer_id) VALUES
(1, 1), (1, 2), (2, 2), (3, 3);

-- 销售记录：
--   P1(机械键盘)   -> 张伟(1)：S1 服务张伟 => 是
--   P2(无线鼠标)   -> 王强(3)：S1/S2 都不服务王强 => 否
--   P4(USB-C扩展坞) -> 王强(3)：S3 服务王强 => 是
--   P3(显示器支架) 未售出
INSERT INTO sale (product_id, customer_id, quantity, amount, sale_time) VALUES
(1, 1, 2, 598.00, '2026-09-20 10:30:00'),
(2, 3, 1, 129.00, '2026-09-22 14:05:00'),
(4, 3, 1, 199.00, '2026-09-25 09:12:00');
