package com.example.qq.fragments;

/**
 * Created by dell on 2019/1/2.
 */

public class UserInfo {

    private int imgID;

    private String name;

    public UserInfo(String name,int imgID){
        this.name = name;
        this.imgID = imgID;
    }

    public int getImgID() {
        return imgID;
    }

    public String getName() {
        return name;
    }
}
