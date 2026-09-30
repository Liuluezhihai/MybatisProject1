package com.lab.entity;

/**
 * 学生实体类，对应表 student
 * sid     int unsigned auto_increment primary key  学生编号
 * sname   varchar(20)   not null                  学生姓名
 * gender  char(1)                                  性别
 * age     tinyint unsigned                         年龄
 * major   varchar(30)                              专业
 * phone   varchar(15)                              手机号
 */
public class Student {

    private Integer sid;
    private String sname;
    private String gender;
    private Integer age;
    private String major;
    private String phone;

    public Student() {
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{sid=" + sid + ", sname='" + sname + '\'' + ", gender='" + gender + '\''
                + ", age=" + age + ", major='" + major + '\'' + ", phone='" + phone + "'}";
    }
}
