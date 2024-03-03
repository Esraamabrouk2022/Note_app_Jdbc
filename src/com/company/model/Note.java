package com.company.model;

import java.time.LocalDate;
import java.util.Date;

public class Note {

    private int id;
    private String title;
    private String description;
    private LocalDate date;

    public Note(String title, String description, LocalDate date) {

        this.title = title;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return title; // Display the title in JList
    }
}
