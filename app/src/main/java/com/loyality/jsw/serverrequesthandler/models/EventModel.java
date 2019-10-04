package com.loyality.jsw.serverrequesthandler.models;

public class EventModel {

    private String eventName;
    private String eventId;
    private String eventImage;
    private String eventDescription;
    private String eventDate;
    private String schemeDuration;
    private String winningCriteria;
    private String rewardWon;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getSchemeDuration() {
        return schemeDuration;
    }

    public void setSchemeDuration(String schemeDuration) {
        this.schemeDuration = schemeDuration;
    }

    public String getWinningCriteria() {
        return winningCriteria;
    }

    public void setWinningCriteria(String winningCriteria) {
        this.winningCriteria = winningCriteria;
    }

    public String getRewardWon() {
        return rewardWon;
    }

    public void setRewardWon(String rewardWon) {
        this.rewardWon = rewardWon;
    }
}
