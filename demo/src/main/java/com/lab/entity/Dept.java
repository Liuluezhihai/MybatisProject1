package com.lab.entity;

/**
 * 部门实体类，对应表 dept
 * deptno      int unsigned auto_increment primary key  部门编号
 * dname       varchar(15)                              部门名称
 * loc         varchar(50)                              部门所在位置
 */
public class Dept {

    private Integer deptno;
    private String dname;
    private String loc;

    public Dept() {
    }

    public Dept(String dname, String loc) {
        this.dname = dname;
        this.loc = loc;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Dept{deptno=" + deptno + ", dname='" + dname + '\'' + ", loc='" + loc + "'}";
    }
}
