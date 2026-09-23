package com.lab.test;

import com.lab.entity.Student;
import com.lab.mapper.StudentMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 第一个 MyBatis 程序：查询全部学生
 */
public class MyBatisDemo {
    public static void main(String[] args) {
        // 1. 开启 SqlSession（用完必须关闭）
        try (SqlSession session = MyBatisUtil.openSession()) {
            // 2. 获取 Mapper 动态代理对象
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            // 3. 执行查询并输出
            List<Student> list = mapper.selectAll();
            System.out.println("=== 全部学生 ===");
            list.forEach(System.out::println);
        }
    }
}
