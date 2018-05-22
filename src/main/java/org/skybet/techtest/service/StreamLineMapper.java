package org.skybet.techtest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.skybet.techtest.model.MessageType;
import org.skybet.techtest.model.message.Message;
import org.skybet.techtest.service.exception.UnknownMessageTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StreamLineMapper {

    Logger log = LoggerFactory.getLogger(StreamLineMapper.class);

    private ObjectMapper objectMapper;

    @Autowired
    public StreamLineMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Message toMessage(String messageLine) {

        List<String> data = splitMessage(messageLine);

        Message message = null;
        try {
            String type = data.get(2);
            message = MessageType.parse(type).toMessage(data);
        } catch (UnknownMessageTypeException e) {
            log.warn("Unknown message type ({}) for message [{}]", data.get(2), messageLine);
        } catch (Exception e) {
            log.error("Error while parsing: [{}] reason: {}", messageLine, e.getMessage());
        }

        return message;
    }

    private List<String> splitMessage(String message) {

        return Arrays.stream(
                Optional.ofNullable(message)
                        .map(this::applyRegex)
                        .get())
                .filter(s -> !s.isEmpty())
                .map(this::removeEscapeChars)
                .collect(Collectors.toList());
    }


    private String removeEscapeChars(String s) {
        return s.replace("\\|", "|");
    }

    private String[] applyRegex(String message) {
        return message.split("[|](?<!\\\\\\|)");
    }

    public String toJson(Message message) throws IOException {
        return objectMapper.writeValueAsString(message);
    }
}