package org.skybet.techtest.model.message;

public class MarketMessage extends Message {

    private final String eventId;
    private final String marketId;
    private final String name;
    private final Boolean displayed;
    private final Boolean suspended;

    public MarketMessage(Integer msgId, String operation, String type, Long timestamp, String eventId, String marketId, String name, Boolean displayed, Boolean suspended) {
        super(msgId, operation, type, timestamp);
        this.eventId = eventId;
        this.marketId = marketId;
        this.name = name;
        this.displayed = displayed;
        this.suspended = suspended;
    }

    public String getEventId() {
        return eventId;
    }

    public String getMarketId() {
        return marketId;
    }

    public String getName() {
        return name;
    }

    public Boolean getDisplayed() {
        return displayed;
    }

    public Boolean getSuspended() {
        return suspended;
    }
}
