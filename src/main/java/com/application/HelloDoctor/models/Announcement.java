package com.application.HelloDoctor.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Announcement")
public class Announcement {

    @Id
    private int id;
    private String announcement;

    public Announcement(){

    }

    public Announcement(int id, String announcement) {
        this.id = id;
        this.announcement = announcement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
}
