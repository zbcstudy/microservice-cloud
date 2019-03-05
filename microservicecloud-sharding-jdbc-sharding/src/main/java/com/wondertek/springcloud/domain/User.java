package com.wondertek.springcloud.domain;

import java.io.Serializable;

/**
 * @Author zbc
 * @Date 14:19-2019/3/3
 */
public class User implements Serializable {

    private static final long serialVersionUID = -663342815207638809L;

    private Long id;

    private String city = "";

    private String name = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
