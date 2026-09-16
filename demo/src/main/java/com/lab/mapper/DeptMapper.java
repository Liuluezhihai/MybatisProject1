package com.lab.mapper;

import com.lab.entity.Dept;

import java.util.List;

/**
 * 部门表 Mapper 接口，MyBatis 通过动态代理生成实现
 */
public interface DeptMapper {

    /** 查询全部部门 */
    List<Dept> selectAll();

    /** 根据部门编号查询 */
    Dept selectById(Integer deptno);

    /** 新增部门，返回受影响行数，自增主键回填到 dept.deptno */
    int insert(Dept dept);

    /** 根据部门编号修改 */
    int update(Dept dept);

    /** 根据部门编号删除 */
    int deleteById(Integer deptno);
}
