package com.huayou.acc.acc.bean;

/**
 * Created by alu on 2017/8/17.
 */

public class P {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public P(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public P() {
    }

}
