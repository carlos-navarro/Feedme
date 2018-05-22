package org.skybet.techtest.model;

import org.junit.Test;
import org.skybet.techtest.service.exception.UnknownMessageTypeException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class MessageTypeTest {


    @Test
    public void parseWithUnknownValue_ThrowUnknownException() {

        assertThatThrownBy(() -> MessageType.parse("uknown-type"))
                .isInstanceOf(UnknownMessageTypeException.class);

    }

    @Test
    public void parseWithValidEventValue_returnExceptedType() {
        assertThat(MessageType.parse("event")).isEqualTo(MessageType.EVENT);
    }

    @Test
    public void parseWithValidMarketValue_returnExceptedType() {
        assertThat(MessageType.parse("market")).isEqualTo(MessageType.MARKET);
    }

    @Test
    public void parseWithValidOutcomeValue_returnExceptedType() {
        assertThat(MessageType.parse("outcome")).isEqualTo(MessageType.OUTCOME);
    }





}