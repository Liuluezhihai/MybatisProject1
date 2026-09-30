package org.example.demo1.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/** MyBatis 工具类：加载全局配置文件，单例 SqlSessionFactory */
public class MyBatisUtil {

    private static final SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        try (InputStream in = Resources.getResourceAsStream("mybatis-config.xml")) {
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("加载 mybatis-config.xml 失败: " + e.getMessage());
        }
    }

    private MyBatisUtil() {
    }

    public static SqlSession openSession() {
        return SQL_SESSION_FACTORY.openSession();
    }
}
