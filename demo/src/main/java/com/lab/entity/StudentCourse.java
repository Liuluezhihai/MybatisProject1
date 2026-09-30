package com.lab.entity;

import java.math.BigDecimal;

/**
 * 选课实体类，对应中间表 student_course
 * sid   int unsigned not null  学生编号（联合主键 + 外键 -> student.sid）
 * cid   int unsigned not null  课程编号（联合主键 + 外键 -> course.cid）
 * score decimal(5,2)           成绩
 */
public class StudentCourse {

    private Integer sid;
    private Integer cid;
    private BigDecimal score;

    public StudentCourse() {
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentCourse{sid=" + sid + ", cid=" + cid + ", score=" + score + "}";
    }
}
