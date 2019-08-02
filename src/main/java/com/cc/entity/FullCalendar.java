package com.cc.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class FullCalendar implements Serializable {

    private String id;
    private String title;
    private String eventColor;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEventColor() {
        return eventColor;
    }

    public void setEventColor(String eventColor) {
        this.eventColor = eventColor;
    }

    public FullCalendar(String id, String title, String eventColor) {
        this.id = id;
        this.title = title;
        this.eventColor = eventColor;
    }
}
