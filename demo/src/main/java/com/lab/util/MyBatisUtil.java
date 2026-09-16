package com.lab.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis 工具类：SqlSessionFactory 全局只构建一次，避免重复创建
 */
public class MyBatisUtil {

    private static final SqlSessionFactory FACTORY;

    static {
        try (InputStream in = Resources.getResourceAsStream("mybatis-config.xml")) {
            FACTORY = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("加载 mybatis-config.xml 失败: " + e.getMessage());
        }
    }

    private MyBatisUtil() {
    }

    /** 获取全局唯一的 SqlSessionFactory */
    public static SqlSessionFactory getFactory() {
        return FACTORY;
    }

    /** 开启一个非自动提交的 SqlSession（增删改需手动 commit） */
    public static SqlSession openSession() {
        return FACTORY.openSession();
    }

    /** 开启一个可指定是否自动提交的 SqlSession */
    public static SqlSession openSession(boolean autoCommit) {
        return FACTORY.openSession(autoCommit);
    }
}
