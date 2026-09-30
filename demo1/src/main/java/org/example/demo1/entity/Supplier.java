package org.example.demo1.entity;

/** 供应商表 supplier */
public class Supplier {

    private Long id;
    /** 供应商名称 */
    private String name;
    /** 联系方式 */
    private String contact;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Supplier{id=" + id + ", name='" + name + "', contact='" + contact + "'}";
    }
}
