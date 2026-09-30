package org.example.demo1.entity;

/** 客户表 customer */
public class Customer {

    private Long id;
    /** 客户姓名 */
    private String name;
    /** 联系电话 */
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + "', phone='" + phone + "'}";
    }
}
