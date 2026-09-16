package com.lab.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 雇员实体类，对应表 emp
 * empno     int unsigned auto_increment primary key  雇员编号
 * ename     varchar(15)                              雇员姓名
 * job       varchar(10)                              雇员职位
 * mgr       int unsigned                             雇员对应领导的编号
 * hiredate  date                                     雇员的雇佣日期
 * sal       decimal(7,2)                             雇员的基本工资
 * comm      decimal(7,2)                             奖金
 * deptno    int unsigned                             所在部门（外键 -> dept.deptno）
 */
public class Emp {

    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private BigDecimal sal;
    private BigDecimal comm;
    private Integer deptno;

    public Emp() {
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Emp{empno=" + empno + ", ename='" + ename + '\'' + ", job='" + job + '\''
                + ", mgr=" + mgr + ", hiredate=" + hiredate + ", sal=" + sal
                + ", comm=" + comm + ", deptno=" + deptno + "}";
    }
}
