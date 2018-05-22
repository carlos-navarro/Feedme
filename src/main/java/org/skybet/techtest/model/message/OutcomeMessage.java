package org.skybet.techtest.model.message;

public class OutcomeMessage extends Message {

    private final String marketId;
    private final String outcomeId;
    private final String name;
    private final String price;
    private final Boolean displayed;
    private final Boolean suspended;

    public OutcomeMessage(Integer msgId, String operation, String type, Long timestamp, String marketId, String outcomeId, String name, String price, Boolean displayed, Boolean suspended) {
        super(msgId, operation, type, timestamp);
        this.marketId = marketId;
        this.outcomeId = outcomeId;
        this.name = name;
        this.price = price;
        this.displayed = displayed;
        this.suspended = suspended;
    }

    public String getMarketId() {
        return marketId;
    }

    public String getOutcomeId() {
        return outcomeId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Boolean getDisplayed() {
        return displayed;
    }

    public Boolean getSuspended() {
        return suspended;
    }
}
