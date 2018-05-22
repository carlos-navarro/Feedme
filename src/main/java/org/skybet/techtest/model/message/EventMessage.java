package org.skybet.techtest.model.message;

public class EventMessage extends Message {

    private final String eventId;
    private final String category;
    private final String subCategory;
    private final String name;
    private final Long startTime;
    private final Boolean displayed;
    private final Boolean suspended;

    public EventMessage(Integer msgId, String operation, String type, Long timestamp, String eventId, String category, String subCategory, String name, Long startTime, Boolean displayed, Boolean suspended) {
        super(msgId, operation, type, timestamp);
        this.eventId = eventId;
        this.category = category;
        this.subCategory = subCategory;
        this.name = name;
        this.startTime = startTime;
        this.displayed = displayed;
        this.suspended = suspended;
    }

    public String getEventId() {
        return eventId;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getName() {
        return name;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Boolean getDisplayed() {
        return displayed;
    }

    public Boolean getSuspended() {
        return suspended;
    }
}