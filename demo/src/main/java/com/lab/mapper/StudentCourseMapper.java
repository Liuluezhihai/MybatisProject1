package com.lab.mapper;

import com.lab.entity.StudentCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 选课表（中间表） Mapper 接口
 */
public interface StudentCourseMapper {

    /** 查询全部选课记录 */
    List<StudentCourse> selectAll();

    /** 根据学生编号查询所有选课记录 */
    List<StudentCourse> selectBySid(Integer sid);

    /** 根据课程编号查询所有选课记录 */
    List<StudentCourse> selectByCid(Integer cid);

    /** 根据学生编号和课程编号查询一条 */
    StudentCourse selectBySidAndCid(@Param("sid") Integer sid, @Param("cid") Integer cid);

    /** 新增选课记录 */
    int insert(StudentCourse sc);

    /** 更新成绩 */
    int updateScore(StudentCourse sc);

    /** 根据学生编号和课程编号删除 */
    int deleteBySidAndCid(@Param("sid") Integer sid, @Param("cid") Integer cid);

    /** 根据学生编号删除所有选课 */
    int deleteBySid(Integer sid);

    /** 根据课程编号删除所有选课 */
    int deleteByCid(Integer cid);
}
