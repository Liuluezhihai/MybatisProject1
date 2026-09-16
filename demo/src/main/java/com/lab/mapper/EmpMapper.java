package com.lab.mapper;

import com.lab.entity.Emp;

import java.util.List;

/**
 * 雇员表 Mapper 接口，MyBatis 通过动态代理生成实现
 */
public interface EmpMapper {

    /** 查询全部雇员 */
    List<Emp> selectAll();

    /** 根据雇员编号查询 */
    Emp selectById(Integer empno);

    /** 新增雇员，返回受影响行数，自增主键回填到 emp.empno */
    int insert(Emp emp);

    /** 根据雇员编号修改 */
    int update(Emp emp);

    /** 根据雇员编号删除 */
    int deleteById(Integer empno);
}
