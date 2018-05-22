package org.skybet.techtest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.skybet.techtest.model.message.EventMessage;
import org.skybet.techtest.model.message.MarketMessage;
import org.skybet.techtest.model.message.Message;
import org.skybet.techtest.model.message.OutcomeMessage;
import org.skybet.techtest.service.exception.UnknownMessageTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class StreamLineMapperTest {

    private String eventLine = "|12651|create|event|1526843439862|ca4e03d1-9e33-448f-b6d3-9a11154a9073|Football|Premier League|\\|Everton\\| vs \\|Arsenal\\||1526843488978|0|1|";
    private String marketLine = "|14329|create|market|1526843941983|c5798a52-bd31-4721-91d3-ade3a23d24c3|5554c057-9be1-4c6b-9c6a-224cd2d5706a|Match Result|0|1|";
    private String outcomeLine = "|14346|create|outcome|1526843941985|0d98bf99-e46f-4308-8901-1112c3286a39|d1634268-748e-463c-8816-c0d2418242be|\\|Nick Kyrgios\\||1/12|0|1|";
    private String unknownLine = "|14346|create|unknown|1526843941985|message-info|";

    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();

    private StreamLineMapper streamLineMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        streamLineMapper = new StreamLineMapper(objectMapper);
    }

    @Test
    public void eventLine_ValidParse() {

        Message message = streamLineMapper.toMessage(eventLine);

        assertThat(message.getMsgId()).isEqualTo(12651);
        assertThat(message).isOfAnyClassIn(EventMessage.class);

    }

    @Test
    public void marketLine_ValidParse() {

        Message message = streamLineMapper.toMessage(marketLine);

        assertThat(message.getMsgId()).isEqualTo(14329);
        assertThat(message).isOfAnyClassIn(MarketMessage.class);

    }

    @Test
    public void outcomeLine_ValidParse() {

        Message message = streamLineMapper.toMessage(outcomeLine);

        assertThat(message.getMsgId()).isEqualTo(14346);
        assertThat(message).isOfAnyClassIn(OutcomeMessage.class);
    }

    @Test
    public void unknownMessageType_ThrowException() {

        Message message = streamLineMapper.toMessage(unknownLine);
        assertThat(message).isNull();
    }

    @Test
    public void unknownMessageFormat_ReturnNull() {

        Message message = streamLineMapper.toMessage("unknonwMessageFormat");

        assertThat(message).isNull();
    }


    @Test
    public void toJson() {
    }
}