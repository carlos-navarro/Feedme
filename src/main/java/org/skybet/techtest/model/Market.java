package org.skybet.techtest.model;

import java.util.ArrayList;
import java.util.List;

public class Market {

    private final String eventId;
    private final String marketId;
    private final String name;
    private final Boolean displayed;
    private final Boolean suspended;

    private List<Outcome> outcomes;

    public Market(String eventId, String marketId, String name, Boolean displayed, Boolean suspended, List<Outcome> outcomes) {
        this.eventId = eventId;
        this.marketId = marketId;
        this.name = name;
        this.displayed = displayed;
        this.suspended = suspended;
        this.outcomes = outcomes;
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

    public List<Outcome> getOutcomes() {
        if (outcomes == null) {
            outcomes = new ArrayList<>();
        }
        return outcomes;
    }

    public void addOutcome(Outcome outcome) {
        getOutcomes().add(outcome);
    }
}
