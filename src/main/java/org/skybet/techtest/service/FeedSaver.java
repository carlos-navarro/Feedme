package org.skybet.techtest.service;

import org.skybet.techtest.model.Event;
import org.skybet.techtest.model.Market;
import org.skybet.techtest.model.MessageType;
import org.skybet.techtest.model.Outcome;
import org.skybet.techtest.model.message.EventMessage;
import org.skybet.techtest.model.message.MarketMessage;
import org.skybet.techtest.model.message.Message;
import org.skybet.techtest.model.message.OutcomeMessage;
import org.skybet.techtest.provider.database.DataBaseProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FeedSaver {

    Logger log = LoggerFactory.getLogger(FeedSaver.class);

    private DataBaseProvider database;
    private MessageMapper mapper;

    @Autowired
    public FeedSaver(DataBaseProvider database, MessageMapper mapper) {
        this.database = database;
        this.mapper = mapper;
    }


    public void save(Message message) {
        /*
        MessageType type = MessageType.parse(message.getType());
        save(type.event())
        */

        switch (MessageType.parse(message.getType())) {

            case EVENT:
                saveEvent(message);
                break;
            case MARKET:
                saveMarket(message);
                break;
            case OUTCOME:
                saveOutcome(message);
                break;
        }
    }

    private void saveEvent(Message message) {
        save(
                Optional.ofNullable(message)
                        .map(m -> (EventMessage) m)
                        .map(mapper::fromEventMessage)
                        .orElseGet(() -> {
                            log.warn("Event can't be saved for messageId:{}", message.getMsgId());
                            return null;
                        })
        );
    }

    private void saveMarket(Message message) {
        save(
                Optional.ofNullable(message)
                        .map(m -> (MarketMessage) m)
                        .map(mapper::fromMarketMessage)
                        .map(this::addMarket)
                        .orElseGet(() -> {
                            log.warn("Market can't be saved for messageId:{}", message.getMsgId());
                            return null;
                        })
        );
    }

    private void saveOutcome(Message message) {
        save(
                Optional.ofNullable(message)
                        .map(m -> (OutcomeMessage) m)
                        .map(mapper::fromOutcomeMessage)
                        .map(this::addOutcome)
                        .orElseGet(() -> {
                            log.warn("Outcome can't be saved on messageId: {}", message.getMsgId());
                            return null;
                        })
        );
    }


    private void save(Event event) {
        if (event != null)
            database.save(event);
    }

    private Event addOutcome(Outcome outcome) {

        return database.findEventContainingMarket(outcome.getMarketId())
                .map(e -> {
                    e.addOutcome(outcome);
                    return e;
                }).orElse(null);
    }


    private Event addMarket(Market market) {

        return database.findOneEvent(market.getEventId())
                .map(e -> {
                    e.addMarket(market);
                    return e;
                }).orElse(null);
    }
}
