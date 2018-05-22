package org.skybet.techtest.model.message;

public abstract class Message {

    private final Integer msgId;
    private final String operation;
    private final String type;
    private final Long timestamp;

    public Message(Integer msgId, String operation, String type, Long timestamp) {
        this.msgId = msgId;
        this.operation = operation;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public String getOperation() {
        return operation;
    }

    public String getType() {
        return type;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
