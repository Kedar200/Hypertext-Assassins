package com.example.hypertextassassins;

public class Complaint {
    String complaint_id;
    String description;
    String type;
    String state;
    String date_of_complaint;
    String Room_no;

    public Complaint(String complaint_id, String description, String type, String state, String date_of_complaint, String room_no) {
        this.complaint_id = complaint_id;
        this.description = description;
        this.type = type;
        this.state = state;
        this.date_of_complaint = date_of_complaint;
        Room_no = room_no;
    }
}
