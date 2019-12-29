package com.afokeeva.findfriend.infoFromServer.Tables;

import java.util.ArrayList;

public class Animal {
    private String id;
    private String age;
    private String name;
    private String description;
    private String type;
    private String img_url;
    private ArrayList<String> listImgUrl;


    public Animal(String id, String age, String name, String description, String type) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.description = description;
        this.type = type;
    }
    public Animal(String age, String name, String description, String type) {
        this.age = age;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Animal(String id, String age, String name, String description, String type, String img_url) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.description = description;
        this.type = type;
        this.img_url = img_url;
    }

    public Animal(String description, ArrayList<String> listImgUrl) {
        this.description = description;
        this.listImgUrl = listImgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public ArrayList<String> getListImgUrl() {
        return listImgUrl;
    }

    public void setListImgUrl(ArrayList<String> listImgUrl) {
        this.listImgUrl = listImgUrl;
    }
}

