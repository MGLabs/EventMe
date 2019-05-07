package com.mglabs.eventme.model;

import android.content.Intent;
import android.widget.TextView;

import java.util.ArrayList;

public class Event {

    private int eventID;
    private String title;
    private String info;
    private int imageResource;

    private ArrayList<Event> eventsList = new ArrayList<>();

    public Event() {

    }

    /**
     * Gets the ID of the event
     * @return The ID of the event.
     */
    public int getEventID() { return eventID; }

    /**
     * Gets the title of the event
     * @return The title of the event.
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the info of the event
     * @return The info of the event.
     */
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Gets the image of the event
     * @return The image of the event.
     */
    public int getImageResource() {
        return imageResource;
    }

    /**
     * Event constructor
     * @param title  Title of event
     * @param info   Info of event
     * @param image  Image of event
     */
    public Event(int eventID, String title, String info, int image) {
        this.eventID = eventID;
        this.title = title;
        this.info = info;
        this.imageResource = image;
    }

    public Event(int eventID) {
        this.eventID = eventID;
    }

    public Event getEventByID(int id) {
        return eventsList.get(id);
    }
}
