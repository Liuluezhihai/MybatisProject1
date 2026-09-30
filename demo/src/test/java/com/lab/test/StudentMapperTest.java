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

public class StudentMapperTest {

    // ========== 学生 CRUD ==========

    @Test
    public void testStudentInsert() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            Student s = new Student();
            s.setSname("赵六");
            s.setGender("男");
            s.setAge(22);
            s.setMajor("大数据");
            s.setPhone("13900009999");
            int rows = mapper.insert(s);
            session.commit();
            Assert.assertEquals(1, rows);
            // 自增主键回填验证
            Assert.assertNotNull(s.getSid());
            System.out.println("新增学生，回填主键 sid = " + s.getSid());
        }
    }

    @Test
    public void testStudentUpdate() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            // 先插入一条测试数据
            Student s = new Student();
            s.setSname("孙七");
            s.setGender("女");
            s.setAge(20);
            s.setMajor("物联网");
            mapper.insert(s);
            session.commit();

            // 修改专业
            s.setMajor("机器学习");
            int rows = mapper.update(s);
            session.commit();
            Assert.assertEquals(1, rows);

            Student updated = mapper.selectById(s.getSid());
            Assert.assertEquals("机器学习", updated.getMajor());
        }
    }

    @Test
    public void testStudentDelete() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            // 插入并删除
            Student s = new Student();
            s.setSname("周八");
            s.setGender("男");
            s.setAge(19);
            s.setMajor("网络工程");
            mapper.insert(s);
            session.commit();

            int rows = mapper.deleteById(s.getSid());
            session.commit();
            Assert.assertEquals(1, rows);
            Assert.assertNull(mapper.selectById(s.getSid()));
        }
    }

    @Test
    public void testStudentSelectByName() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            // 先插入两条测试数据
            Student s1 = new Student(); s1.setSname("林小明"); s1.setGender("男"); s1.setAge(20); s1.setMajor("计算机");
            Student s2 = new Student(); s2.setSname("林小晓"); s2.setGender("女"); s2.setAge(19); s2.setMajor("软件工程");
            mapper.insert(s1); mapper.insert(s2);
            session.commit();

            // 模糊查询 "林"
            List<Student> list = mapper.selectByName("林");
            Assert.assertTrue(list.size() >= 2);
            list.forEach(System.out::println);
        }
    }

    // ========== 课程 CRUD ==========

    @Test
    public void testCourseInsert() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            CourseMapper mapper = session.getMapper(CourseMapper.class);
            Course c = new Course();
            c.setCname("Spring 框架");
            c.setTeacher("吴老师");
            c.setCredit(new BigDecimal("4.0"));
            c.setHours(64);
            int rows = mapper.insert(c);
            session.commit();
            Assert.assertEquals(1, rows);
            Assert.assertNotNull(c.getCid());
            System.out.println("新增课程，回填主键 cid = " + c.getCid());
        }
    }

    @Test
    public void testCourseUpdate() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            CourseMapper mapper = session.getMapper(CourseMapper.class);
            Course c = new Course();
            c.setCname("Python 编程");
            c.setTeacher("郑老师");
            c.setCredit(new BigDecimal("3.0"));
            c.setHours(48);
            mapper.insert(c);
            session.commit();

            c.setHours(60);
            int rows = mapper.update(c);
            session.commit();
            Assert.assertEquals(1, rows);

            Course updated = mapper.selectById(c.getCid());
            Assert.assertEquals(Integer.valueOf(60), updated.getHours());
        }
    }

    @Test
    public void testCourseDelete() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            CourseMapper mapper = session.getMapper(CourseMapper.class);
            Course c = new Course();
            c.setCname("临时课程");
            c.setTeacher("测试");
            c.setCredit(new BigDecimal("1.0"));
            c.setHours(16);
            mapper.insert(c);
            session.commit();

            int rows = mapper.deleteById(c.getCid());
            session.commit();
            Assert.assertEquals(1, rows);
        }
    }

    // ========== 选课表（多对多）CRUD ==========

    @Test
    public void testStudentCourseInsert() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            CourseMapper courseMapper = session.getMapper(CourseMapper.class);
            StudentCourseMapper scMapper = session.getMapper(StudentCourseMapper.class);

            // 准备：先插入学生和课程
            Student s = new Student(); s.setSname("选课学生"); s.setGender("男"); s.setAge(21); s.setMajor("测试");
            Course c  = new Course();  c.setCname("选课课程");  c.setTeacher("X老师"); c.setCredit(new BigDecimal("2.0")); c.setHours(32);
            studentMapper.insert(s); courseMapper.insert(c);
            session.commit();

            // 再插入选课记录
            StudentCourse sc = new StudentCourse();
            sc.setSid(s.getSid());
            sc.setCid(c.getCid());
            sc.setScore(new BigDecimal("88.5"));
            int rows = scMapper.insert(sc);
            session.commit();
            Assert.assertEquals(1, rows);

            // 查询验证
            StudentCourse found = scMapper.selectBySidAndCid(s.getSid(), c.getCid());
            Assert.assertNotNull(found);
            Assert.assertEquals(new BigDecimal("88.5"), found.getScore());
        }
    }

    @Test
    public void testStudentCourseUpdateScore() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            CourseMapper courseMapper = session.getMapper(CourseMapper.class);
            StudentCourseMapper scMapper = session.getMapper(StudentCourseMapper.class);

            Student s = new Student(); s.setSname("改分学生"); s.setGender("女"); s.setAge(20); s.setMajor("改分");
            Course c  = new Course();  c.setCname("改分课程");  c.setTeacher("Y老师"); c.setCredit(new BigDecimal("2.0")); c.setHours(32);
            studentMapper.insert(s); courseMapper.insert(c);
            session.commit();

            StudentCourse sc = new StudentCourse();
            sc.setSid(s.getSid()); sc.setCid(c.getCid()); sc.setScore(new BigDecimal("60"));
            scMapper.insert(sc);
            session.commit();

            // 补考改分
            sc.setScore(new BigDecimal("75.5"));
            int rows = scMapper.updateScore(sc);
            session.commit();
            Assert.assertEquals(1, rows);
            Assert.assertEquals(new BigDecimal("75.5"), scMapper.selectBySidAndCid(s.getSid(), c.getCid()).getScore());
        }
    }

    @Test
    public void testStudentCourseDelete() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            CourseMapper courseMapper = session.getMapper(CourseMapper.class);
            StudentCourseMapper scMapper = session.getMapper(StudentCourseMapper.class);

            Student s = new Student(); s.setSname("退课学生"); s.setGender("男"); s.setAge(19); s.setMajor("退课");
            Course c  = new Course();  c.setCname("退课课程");  c.setTeacher("Z老师"); c.setCredit(new BigDecimal("1.0")); c.setHours(16);
            studentMapper.insert(s); courseMapper.insert(c);
            session.commit();

            StudentCourse sc = new StudentCourse();
            sc.setSid(s.getSid()); sc.setCid(c.getCid()); sc.setScore(null);
            scMapper.insert(sc);
            session.commit();

            int rows = scMapper.deleteBySidAndCid(s.getSid(), c.getCid());
            session.commit();
            Assert.assertEquals(1, rows);
            Assert.assertNull(scMapper.selectBySidAndCid(s.getSid(), c.getCid()));
        }
    }

    @Test
    public void testQueryStudentAllCourses() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            // 查询 sid=1 学生的所有选课记录（演示多对多关联查询场景）
            StudentCourseMapper scMapper = session.getMapper(StudentCourseMapper.class);
            List<StudentCourse> records = scMapper.selectBySid(1);
            System.out.println("=== sid=1 学生的选课记录 ===");
            records.forEach(System.out::println);
        }
    }

    // ========== 动态 SQL（任务 3）==========

    @Test
    public void testSelectByCondition() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper mapper = session.getMapper(StudentMapper.class);

            // 准备：插入 2 条已知数据
            Student a = new Student(); a.setSname("动态查询张"); a.setGender("男"); a.setAge(20); a.setMajor("计算机");
            Student b = new Student(); b.setSname("动态查询李"); b.setGender("女"); b.setAge(21); b.setMajor("软件工程");
            mapper.insert(a); mapper.insert(b);
            session.commit();

            // 条件 1：只传姓名（模糊）
            Student cond1 = new Student();
            cond1.setSname("动态查询");
            List<Student> r1 = mapper.selectByCondition(cond1);
            Assert.assertTrue(r1.size() >= 2);
            System.out.println("=== 按姓名模糊查询 '动态查询' ===");
            r1.forEach(System.out::println);

            // 条件 2：姓名 + 性别
            Student cond2 = new Student();
            cond2.setSname("动态查询");
            cond2.setGender("男");
            List<Student> r2 = mapper.selectByCondition(cond2);
            Assert.assertTrue(r2.size() >= 1);
            Assert.assertEquals("男", r2.get(0).getGender());
            System.out.println("=== 按姓名+性别查询 ===");
            r2.forEach(System.out::println);

            // 条件 3：什么都不传（应查全部）
            Student cond3 = new Student();
            List<Student> r3 = mapper.selectByCondition(cond3);
            Assert.assertTrue(r3.size() >= 2);
            System.out.println("=== 无任何条件（查全部）===");
            r3.forEach(System.out::println);
        }
    }

    @Test
    public void testUpdateDynamic() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper mapper = session.getMapper(StudentMapper.class);

            // 先插入一条
            Student s = new Student();
            s.setSname("动态更新测试");
            s.setGender("男");
            s.setAge(20);
            s.setMajor("原专业");
            mapper.insert(s);
            session.commit();

            // 只更新 major，其他字段为 null —— set 标签应只拼 major
            Student patch = new Student();
            patch.setSid(s.getSid());
            patch.setMajor("新专业");
            int rows = mapper.updateDynamic(patch);
            session.commit();
            Assert.assertEquals(1, rows);

            // 验证：major 改了，gender 和 age 保持原值
            Student updated = mapper.selectById(s.getSid());
            Assert.assertEquals("新专业", updated.getMajor());
            Assert.assertEquals("男", updated.getGender());   // 未被覆盖
            Assert.assertEquals(Integer.valueOf(20), updated.getAge()); // 未被覆盖

            System.out.println("=== 动态更新后 ===");
            System.out.println(updated);
        }
    }

    @Test
    public void testInsertBatch() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            java.util.ArrayList<Student> batch = new java.util.ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                Student s = new Student();
                s.setSname("批量学生" + i);
                s.setGender(i % 2 == 1 ? "男" : "女");
                s.setAge(18 + i);
                s.setMajor("批量测试");
                batch.add(s);
            }
            int rows = mapper.insertBatch(batch);
            session.commit();
            Assert.assertEquals(3, rows);

            // 验证批量插入结果：用 sname="批量" 模糊查询，能匹配全部 3 条
            Student v = new Student(); v.setSname("批量");
            List<Student> list = mapper.selectByCondition(v);
            Assert.assertTrue(list.size() >= 3);
            System.out.println("=== 批量插入后查询 ===");
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testDeleteBatch() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper mapper = session.getMapper(StudentMapper.class);

            // 先插 2 条，拿到 sid
            Student s1 = new Student(); s1.setSname("批量删A"); s1.setGender("男"); s1.setAge(19); s1.setMajor("删除测试");
            Student s2 = new Student(); s2.setSname("批量删B"); s2.setGender("女"); s2.setAge(20); s2.setMajor("删除测试");
            mapper.insert(s1); mapper.insert(s2);
            session.commit();

            // 批量删除
            java.util.List<Integer> ids = java.util.Arrays.asList(s1.getSid(), s2.getSid());
            int rows = mapper.deleteBatch(ids);
            session.commit();
            Assert.assertEquals(2, rows);

            // 验证
            Assert.assertNull(mapper.selectById(s1.getSid()));
            Assert.assertNull(mapper.selectById(s2.getSid()));
            System.out.println("=== 批量删除成功 ===");
        }
    }

    @Test
    public void testSelectByChoose() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            StudentMapper mapper = session.getMapper(StudentMapper.class);

            // 准备：插一条已知姓名和专业
            Student s = new Student(); s.setSname("精确匹配王"); s.setGender("男"); s.setAge(22); s.setMajor("精确匹配专业");
            mapper.insert(s); session.commit();

            // 分支 1：传了姓名 → 精确查姓名
            Student cond1 = new Student(); cond1.setSname("精确匹配王");
            List<Student> r1 = mapper.selectByChoose(cond1);
            Assert.assertTrue(r1.size() >= 1);
            System.out.println("=== choose 分支1：按姓名精确查 ===");
            r1.forEach(System.out::println);

            // 分支 2：不传姓名但传专业 → 按专业查
            Student cond2 = new Student(); cond2.setMajor("精确匹配专业");
            List<Student> r2 = mapper.selectByChoose(cond2);
            Assert.assertTrue(r2.size() >= 1);
            System.out.println("=== choose 分支2：按专业查 ===");
            r2.forEach(System.out::println);

            // 分支 3：什么都不传 → 查全部
            Student cond3 = new Student();
            List<Student> r3 = mapper.selectByChoose(cond3);
            Assert.assertTrue(r3.size() >= 1);
            System.out.println("=== choose 分支3：查全部 ===");
            r3.forEach(System.out::println);
        }
    }
}
