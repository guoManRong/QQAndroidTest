package com.example.qq.fragments;

/**
 * Created by dell on 2019/1/2.
 */

public class ContactInfo {

    private String name;

    private String tel;

    public ContactInfo(String name, String tel){
        this.name = name;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }
}
