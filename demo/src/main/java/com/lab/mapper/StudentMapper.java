package com.lab.mapper;

import com.lab.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生表 Mapper 接口
 */
public interface StudentMapper {

    /** 查询全部学生 */
    List<Student> selectAll();

    /** 根据学生编号查询 */
    Student selectById(Integer sid);

    /** 根据姓名模糊查询 */
    List<Student> selectByName(String sname);

    /** 新增学生，自增主键回填到 student.sid */
    int insert(Student student);

    /** 修改学生信息 */
    int update(Student student);

    /** 根据学生编号删除 */
    int deleteById(Integer sid);

    // ========== 以下为动态 SQL 方法（任务 3）==========

    /**
     * 多条件模糊查询（if + where 标签）
     * 只有非空条件才会拼入 WHERE 子句
     */
    List<Student> selectByCondition(Student student);

    /**
     * 动态更新（set 标签）
     * 只更新非 null 字段，自动去除末尾多余逗号
     */
    int updateDynamic(Student student);

    // ========== foreach 批量操作 ==========

    /**
     * 批量删除（foreach + IN）
     */
    int deleteBatch(@Param("sids") List<Integer> sids);

    /**
     * 批量插入（foreach + VALUES (...),(...),...）
     */
    int insertBatch(@Param("list") List<Student> students);

    // ========== choose/when/otherwise 分支选择 ==========

    /**
     * 优先按姓名精确查询，否则按专业查询，否则查询全部
     */
    List<Student> selectByChoose(Student student);
}
