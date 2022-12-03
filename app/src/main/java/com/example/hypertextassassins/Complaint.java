package com.example.hypertextassassins;

public class Complaint {
    String email;
    String complaint_id;
    String description;
    String type;
    String state;
    String date_of_complaint;
    String Room_no;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(String complaint_id) {
        this.complaint_id = complaint_id;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate_of_complaint() {
        return date_of_complaint;
    }

    public void setDate_of_complaint(String date_of_complaint) {
        this.date_of_complaint = date_of_complaint;
    }

    public String getRoom_no() {
        return Room_no;
    }

    public void setRoom_no(String room_no) {
        Room_no = room_no;
    }

    public Complaint(String email, String complaint_id, String description, String type, String state, String date_of_complaint, String room_no) {
        this.email = email;
        this.complaint_id = complaint_id;
        this.description = description;
        this.type = type;
        this.state = state;
        this.date_of_complaint = date_of_complaint;
        Room_no = room_no;
    }
}
