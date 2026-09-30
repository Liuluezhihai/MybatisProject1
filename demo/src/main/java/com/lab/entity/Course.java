package com.lab.entity;

import java.math.BigDecimal;

/**
 * 课程实体类，对应表 course
 * cid      int unsigned auto_increment primary key  课程编号
 * cname    varchar(30)   not null                  课程名称
 * teacher  varchar(20)                              授课老师
 * credit   decimal(3,1)                             学分
 * hours    int unsigned                             课时
 */
public class Course {

    private Integer cid;
    private String cname;
    private String teacher;
    private BigDecimal credit;
    private Integer hours;

    public Course() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Course{cid=" + cid + ", cname='" + cname + '\'' + ", teacher='" + teacher + '\''
                + ", credit=" + credit + ", hours=" + hours + "}";
    }
}
