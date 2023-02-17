package com.example.LibraryManagementSystem.DTOS;

public class StudentUpdateRequestDto {
    private int id;
    private String mobNo;

    public StudentUpdateRequestDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }
}
