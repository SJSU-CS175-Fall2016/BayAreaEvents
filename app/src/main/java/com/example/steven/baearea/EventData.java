package com.example.steven.baearea;

/**
 * Created by Joan on 12/5/16.
 */
public class EventData {

    private String eventCategory;
    private String eventName;
    private String eventURL;
    private String eventCap;
    private String eventImg;
    private String eventDate;

    public EventData(String eventCategory, String eventName,String eventURL, String eventCap, String eventImg){
        super();
        this.eventCategory= eventCategory;
        this.eventName= eventName;
        this.eventURL = eventURL;
        this.eventCap = eventCap;
        this.eventImg = eventImg;
        this.eventDate= eventDate;


    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventCap() {

        return eventCap;

    }

    public void setEventCap(String eventCap) {
        this.eventCap = eventCap;
    }

    public String getEventURL() {

        return eventURL;
    }

    public void setEventURL(String eventURL) {
        this.eventURL = eventURL;
    }

    public String getEventName() {

        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCategory() {

        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }
}
