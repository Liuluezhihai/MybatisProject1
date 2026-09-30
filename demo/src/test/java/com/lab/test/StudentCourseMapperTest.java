package com.lab.test;

import com.lab.entity.Course;
import com.lab.entity.Student;
import com.lab.entity.StudentCourse;
import com.lab.mapper.CourseMapper;
import com.lab.mapper.StudentCourseMapper;
import com.lab.mapper.StudentMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * 选课表（中间表）Mapper 测试
 * student_course 表有外键约束（sid -> student.sid, cid -> course.cid），
 * 所以每个测试先通过 StudentMapper/CourseMapper 插入一条真实的学生和课程。
 */
public class StudentCourseMapperTest {

    /** 辅助方法：插入一条学生和一门课程，返回 [sid, cid] */
    private int[] prepareStudentAndCourse(SqlSession session) {
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        CourseMapper courseMapper = session.getMapper(CourseMapper.class);

        Student s = new Student();
        s.setSname("选课测试学生");
        s.setGender("男");
        s.setAge(20);
        s.setMajor("软件工程");
        studentMapper.insert(s);

        Course c = new Course();
        c.setCname("选课测试课程");
        c.setTeacher("冯老师");
        c.setCredit(new BigDecimal("2.0"));
        c.setHours(32);
        courseMapper.insert(c);

        session.commit();
        return new int[]{s.getSid(), c.getCid()};
    }

    // ========== 选课记录 CRUD ==========

    @Test
    public void testScInsert() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            int[] ids = prepareStudentAndCourse(session);
            StudentCourseMapper mapper = session.getMapper(StudentCourseMapper.class);

            StudentCourse sc = new StudentCourse();
            sc.setSid(ids[0]);
            sc.setCid(ids[1]);
            sc.setScore(new BigDecimal("88.50"));
            int rows = mapper.insert(sc);
            session.commit();
            Assert.assertEquals(1, rows);
            System.out.println("新增选课记录：sid=" + sc.getSid() + ", cid=" + sc.getCid() + ", score=" + sc.getScore());
        }
    }

    @Test
    public void testScUpdateScore() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            int[] ids = prepareStudentAndCourse(session);
            StudentCourseMapper mapper = session.getMapper(StudentCourseMapper.class);

            StudentCourse sc = new StudentCourse();
            sc.setSid(ids[0]);
            sc.setCid(ids[1]);
            sc.setScore(new BigDecimal("60.00"));
            mapper.insert(sc);
            session.commit();

            // 更新成绩
            sc.setScore(new BigDecimal("95.50"));
            int rows = mapper.updateScore(sc);
            session.commit();
            Assert.assertEquals(1, rows);

            StudentCourse updated = mapper.selectBySidAndCid(ids[0], ids[1]);
            Assert.assertEquals(new BigDecimal("95.50"), updated.getScore());
            System.out.println("更新后选课记录 = " + updated);
        }
    }

    // ========== 选课记录查询 ==========

    @Test
    public void testScSelectAll() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentCourseMapper mapper = session.getMapper(StudentCourseMapper.class);
            List<StudentCourse> list = mapper.selectAll();
            Assert.assertNotNull(list);
            System.out.println("=== 全部选课记录，共 " + list.size() + " 条 ===");
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testScSelectBySidAndCid() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            int[] ids = prepareStudentAndCourse(session);
            StudentCourseMapper mapper = session.getMapper(StudentCourseMapper.class);

            StudentCourse sc = new StudentCourse();
            sc.setSid(ids[0]);
            sc.setCid(ids[1]);
            sc.setScore(new BigDecimal("77.00"));
            mapper.insert(sc);
            session.commit();

            StudentCourse loaded = mapper.selectBySidAndCid(ids[0], ids[1]);
            Assert.assertNotNull(loaded);
            Assert.assertEquals(new BigDecimal("77.00"), loaded.getScore());
            System.out.println("按联合主键查询 = " + loaded);
        }
    }

    @Test
    public void testScSelectByCid() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            int[] ids = prepareStudentAndCourse(session);
            StudentCourseMapper mapper = session.getMapper(StudentCourseMapper.class);

            StudentCourse sc = new StudentCourse();
            sc.setSid(ids[0]);
            sc.setCid(ids[1]);
            sc.setScore(new BigDecimal("66.00"));
            mapper.insert(sc);
            session.commit();

            List<StudentCourse> list = mapper.selectByCid(ids[1]);
            Assert.assertTrue(list.size() >= 1);
            System.out.println("=== cid=" + ids[1] + " 的选课记录，共 " + list.size() + " 条 ===");
            list.forEach(System.out::println);
        }
    }

    // ========== 选课记录删除 ==========

    @Test
    public void testScDeleteBySidAndCid() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            int[] ids = prepareStudentAndCourse(session);
            StudentCourseMapper mapper = session.getMapper(StudentCourseMapper.class);

            StudentCourse sc = new StudentCourse();
            sc.setSid(ids[0]);
            sc.setCid(ids[1]);
            sc.setScore(new BigDecimal("50.00"));
            mapper.insert(sc);
            session.commit();

            int rows = mapper.deleteBySidAndCid(ids[0], ids[1]);
            session.commit();
            Assert.assertEquals(1, rows);
            Assert.assertNull(mapper.selectBySidAndCid(ids[0], ids[1]));
            System.out.println("按联合主键删除成功");
        }
    }

    @Test
    public void testScDeleteBySid() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            int[] ids = prepareStudentAndCourse(session);
            StudentCourseMapper mapper = session.getMapper(StudentCourseMapper.class);

            // 给同一个学生插两条选课记录
            StudentCourse sc1 = new StudentCourse();
            sc1.setSid(ids[0]);
            sc1.setCid(ids[1]);
            sc1.setScore(new BigDecimal("80.00"));
            mapper.insert(sc1);
            session.commit();

            int rows = mapper.deleteBySid(ids[0]);
            session.commit();
            Assert.assertEquals(1, rows);
            Assert.assertTrue(mapper.selectBySid(ids[0]).isEmpty());
            System.out.println("按 sid 删除该学生全部选课成功");

            // 清理学生数据（此时已无选课记录，可直接删）
            session.getMapper(StudentMapper.class).deleteById(ids[0]);
            session.getMapper(CourseMapper.class).deleteById(ids[1]);
            session.commit();
        }
    }
}
