package com.example.hypertextassassins;

import android.hardware.usb.UsbRequest;

public class User {
    String student_id;
    String name;
    String phone_number;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public User(){

    }
    public User(String student_id, String name, String phone_number) {
        this.student_id = student_id;
        this.name = name;
        this.phone_number = phone_number;
    }
}
