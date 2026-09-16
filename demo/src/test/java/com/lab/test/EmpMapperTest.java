package com.lab.test;

import com.lab.entity.Dept;
import com.lab.entity.Emp;
import com.lab.mapper.DeptMapper;
import com.lab.mapper.EmpMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class EmpMapperTest {

    /** 先插入一个部门并提交，返回其自增主键，供雇员外键 deptno 使用 */
    private int prepareDept() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            DeptMapper deptMapper = session.getMapper(DeptMapper.class);
            Dept dept = new Dept("研发部", "北京");
            deptMapper.insert(dept);
            session.commit();
            return dept.getDeptno();
        }
    }

    @Test
    public void testInsert() {
        int deptno = prepareDept();
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = new Emp();
            emp.setEname("陈晨");
            emp.setJob("测试工程师");
            emp.setMgr(null);
            emp.setHiredate(new Date());
            emp.setSal(new BigDecimal("9000"));
            emp.setComm(new BigDecimal("500"));
            emp.setDeptno(deptno);
            int rows = mapper.insert(emp);
            session.commit();          // 增删改必须提交事务
            Assert.assertEquals(1, rows);
            System.out.println("回填主键 empno = " + emp.getEmpno());
        }
    }

    @Test
    public void testUpdate() {
        int deptno = prepareDept();
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            // 新增一条测试数据，保证测试自包含
            Emp emp = new Emp();
            emp.setEname("李雷");
            emp.setJob("开发工程师");
            emp.setHiredate(new Date());
            emp.setSal(new BigDecimal("10000"));
            emp.setDeptno(deptno);
            mapper.insert(emp);
            session.commit();

            // 修改并断言
            emp.setSal(new BigDecimal("13000"));
            int rows = mapper.update(emp);
            session.commit();
            Assert.assertEquals(1, rows);

            Emp updated = mapper.selectById(emp.getEmpno());
            Assert.assertEquals(new BigDecimal("13000"), updated.getSal());
        }
    }

    @Test
    public void testDelete() {
        int deptno = prepareDept();
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            // 新增一条测试数据，保证测试自包含
            Emp emp = new Emp();
            emp.setEname("韩梅梅");
            emp.setJob("实施工程师");
            emp.setHiredate(new Date());
            emp.setSal(new BigDecimal("8000"));
            emp.setDeptno(deptno);
            mapper.insert(emp);
            session.commit();

            int rows = mapper.deleteById(emp.getEmpno());
            session.commit();
            Assert.assertEquals(13000, emp.getSal().intValue());
            Assert.assertNull(mapper.selectById(emp.getEmpno()));
        }
    }
}
