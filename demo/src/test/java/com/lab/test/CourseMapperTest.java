package com.lab.test;

import com.lab.entity.Course;
import com.lab.mapper.CourseMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class CourseMapperTest {

    // ========== 课程 CRUD ==========

    @Test
    public void testCourseInsert() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            CourseMapper mapper = session.getMapper(CourseMapper.class);
            Course c = new Course();
            c.setCname("软件测试");
            c.setTeacher("郑老师");
            c.setCredit(new BigDecimal("2.5"));
            c.setHours(32);
            int rows = mapper.insert(c);
            session.commit();
            Assert.assertEquals(1, rows);
            // 自增主键回填验证
            Assert.assertNotNull(c.getCid());
            System.out.println("新增课程，回填主键 cid = " + c.getCid());
        }
    }

    @Test
    public void testCourseUpdate() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            CourseMapper mapper = session.getMapper(CourseMapper.class);
            // 先插入一条测试数据
            Course c = new Course();
            c.setCname("测试课程A");
            c.setTeacher("钱老师");
            c.setCredit(new BigDecimal("2.0"));
            c.setHours(32);
            mapper.insert(c);
            session.commit();

            // 修改学分和课时
            c.setCredit(new BigDecimal("3.5"));
            c.setHours(48);
            int rows = mapper.update(c);
            session.commit();
            Assert.assertEquals(1, rows);

            Course updated = mapper.selectById(c.getCid());
            Assert.assertEquals(new BigDecimal("3.5"), updated.getCredit());
            Assert.assertEquals(Integer.valueOf(48), updated.getHours());
            System.out.println("修改后课程 = " + updated);
        }
    }

    @Test
    public void testCourseDelete() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            CourseMapper mapper = session.getMapper(CourseMapper.class);
            // 插入并删除
            Course c = new Course();
            c.setCname("待删课程");
            c.setTeacher("吴老师");
            c.setCredit(new BigDecimal("1.0"));
            c.setHours(16);
            mapper.insert(c);
            session.commit();

            int rows = mapper.deleteById(c.getCid());
            session.commit();
            Assert.assertEquals(1, rows);
            Assert.assertNull(mapper.selectById(c.getCid()));
            System.out.println("删除课程 cid = " + c.getCid() + " 成功");
        }
    }

    // ========== 课程查询 ==========

    @Test
    public void testCourseSelectAll() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            CourseMapper mapper = session.getMapper(CourseMapper.class);
            List<Course> list = mapper.selectAll();
            Assert.assertNotNull(list);
            System.out.println("=== 全部课程，共 " + list.size() + " 条 ===");
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testCourseSelectById() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            CourseMapper mapper = session.getMapper(CourseMapper.class);
            // 插入一条再按主键查
            Course c = new Course();
            c.setCname("主键查询课程");
            c.setTeacher("陈老师");
            c.setCredit(new BigDecimal("2.0"));
            c.setHours(40);
            mapper.insert(c);
            session.commit();

            Course loaded = mapper.selectById(c.getCid());
            Assert.assertNotNull(loaded);
            Assert.assertEquals("主键查询课程", loaded.getCname());
            Assert.assertEquals("陈老师", loaded.getTeacher());
            System.out.println("按主键查询 = " + loaded);
        }
    }
}
