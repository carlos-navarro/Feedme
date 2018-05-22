package org.skybet.techtest.model;

import org.skybet.techtest.model.message.EventMessage;
import org.skybet.techtest.model.message.MarketMessage;
import org.skybet.techtest.model.message.Message;
import org.skybet.techtest.model.message.OutcomeMessage;
import org.skybet.techtest.service.exception.UnknownMessageTypeException;

import java.util.List;

public enum MessageType {

    EVENT {
        @Override
        public Message toMessage(List<String> data) {
            return new EventMessage(Integer.parseInt(data.get(0)), data.get(1), data.get(2), Long.parseLong(data.get(3)),
                    data.get(4), data.get(5), data.get(6), data.get(7), Long.parseLong(data.get(8)), Boolean.parseBoolean(data.get(9)), Boolean.parseBoolean(data.get(10)));
        }
    }, MARKET {
        @Override
        public Message toMessage(List<String> data) {
            return new MarketMessage(Integer.parseInt(data.get(0)), data.get(1), data.get(2), Long.parseLong(data.get(3)),
                    data.get(4), data.get(5), data.get(6), Boolean.parseBoolean(data.get(7)), Boolean.parseBoolean(data.get(8)));
        }
    }, OUTCOME {
        @Override
        public Message toMessage(List<String> data) {
            return new OutcomeMessage(Integer.parseInt(data.get(0)), data.get(1), data.get(2), Long.parseLong(data.get(3)),
                    data.get(4), data.get(5), data.get(6), data.get(7), Boolean.parseBoolean(data.get(8)), Boolean.parseBoolean(data.get(9)));
        }
    };

    public abstract Message toMessage(List<String> data);

    public static MessageType parse(String type) {
        try {
            return MessageType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownMessageTypeException();
        }
    }
}