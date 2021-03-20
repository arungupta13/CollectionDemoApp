package com.example.demoproject;

public class EmployeeData {

    private String name;
    private int imgId;
    private int id;


    public EmployeeData(String name, int imgId, int id) {
        this.name = name;
        this.imgId = imgId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
