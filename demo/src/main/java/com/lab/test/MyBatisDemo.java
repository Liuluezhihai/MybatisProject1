package com.lab.test;

import com.lab.entity.Emp;
import com.lab.mapper.EmpMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 第一个 MyBatis 程序：查询全部雇员
 */
public class MyBatisDemo {
    public static void main(String[] args) {
        // 1. 开启 SqlSession（用完必须关闭）
        try (SqlSession session = MyBatisUtil.openSession()) {
            // 2. 获取 Mapper 动态代理对象
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            // 3. 执行查询并输出
            List<Emp> list = mapper.selectAll();
            list.forEach(System.out::println);
        }
    }
}
