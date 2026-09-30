package com.lab.mapper;

import com.lab.entity.Course;

import java.util.List;

/**
 * 课程表 Mapper 接口
 */
public interface CourseMapper {

    /** 查询全部课程 */
    List<Course> selectAll();

    /** 根据课程编号查询 */
    Course selectById(Integer cid);

    /** 新增课程，自增主键回填到 course.cid */
    int insert(Course course);

    /** 修改课程信息 */
    int update(Course course);

    /** 根据课程编号删除 */
    int deleteById(Integer cid);
}
