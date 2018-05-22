package org.skybet.techtest.service;

import org.skybet.techtest.model.Event;
import org.skybet.techtest.model.Market;
import org.skybet.techtest.model.Outcome;
import org.skybet.techtest.model.message.EventMessage;
import org.skybet.techtest.model.message.MarketMessage;
import org.skybet.techtest.model.message.OutcomeMessage;
import org.springframework.stereotype.Controller;

import java.util.Collections;

@Controller
public class MessageMapper {

    public Event fromEventMessage(EventMessage message) {
        return new Event(
                message.getEventId(), message.getCategory(),
                message.getSubCategory(), message.getName(),
                message.getStartTime(), message.getDisplayed(),
                message.getSuspended(), Collections.emptyList());
    }

    public Market fromMarketMessage(MarketMessage message) {
        return new Market(
                message.getEventId(), message.getMarketId(),
                message.getName(), message.getDisplayed(),
                message.getSuspended(), Collections.emptyList());
    }

    public Outcome fromOutcomeMessage(OutcomeMessage message) {
        return new Outcome(
                message.getMarketId(), message.getOutcomeId(),
                message.getName(), message.getPrice(), message.getDisplayed(),
                message.getSuspended());
    }
}