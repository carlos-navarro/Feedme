package org.skybet.techtest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "events")
public class Event {

    @Id
    private String eventId;
    private String category;
    private String subCategory;
    private String name;
    private Long startTime;
    private Boolean displayed;
    private Boolean suspended;

    private List<Market> markets;

    public Event(String eventId, String category, String subCategory, String name, Long startTime, Boolean displayed, Boolean suspended, List<Market> markets) {
        this.eventId = eventId;
        this.category = category;
        this.subCategory = subCategory;
        this.name = name;
        this.startTime = startTime;
        this.displayed = displayed;
        this.suspended = suspended;
        this.markets = markets;
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

    public List<Market> getMarkets() {
        if (markets == null) {
            markets = new ArrayList<>();
        }
        return markets;
    }

    public void addMarket(Market market) {
        getMarkets().add(market);
    }

    public void addOutcome(Outcome outcome) {
        Market market = markets.stream()
                .filter(m -> m.getMarketId().equals(outcome.getMarketId()))
                .findFirst().get();

        market.addOutcome(outcome);
    }
}
