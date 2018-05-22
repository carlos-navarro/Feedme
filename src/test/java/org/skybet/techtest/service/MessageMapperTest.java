package org.skybet.techtest.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.skybet.techtest.model.Event;
import org.skybet.techtest.model.Market;
import org.skybet.techtest.model.Outcome;
import org.skybet.techtest.model.message.EventMessage;
import org.skybet.techtest.model.message.MarketMessage;
import org.skybet.techtest.model.message.OutcomeMessage;


public class MessageMapperTest {

    public static final String EVENT_ID = "event-id";
    public static final String CATEGORY = "category";
    public static final String SUB_CATEGORY = "sub-category";
    public static final String EVENT_NAME = "event-name";
    public static final long START_TIME = 123456789l;
    public static final long TIMESTAMP = 123456789l;
    public static final String TYPE = "type";
    public static final String OPERATION = "operation";
    public static final int MSG_ID = 1;
    public static final boolean DISPLAYED = true;
    public static final boolean SUSPENDED = false;
    public static final String MARKET_ID = "market-id";
    public static final String MARKET_NAME = "market-name";
    public static final String OUTCOME_ID = "outcome-id";
    public static final String OUTCOME_NAME = "outcome-name";
    public static final String PRICE = "price";
    private MessageMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new MessageMapper();
    }

    @Test
    public void fromEventMessage_returnExpectedEvent() {

        EventMessage eventMessage = new EventMessage(MSG_ID, OPERATION, TYPE, TIMESTAMP, EVENT_ID, CATEGORY, SUB_CATEGORY, EVENT_NAME, START_TIME, DISPLAYED, SUSPENDED);

        Event result = mapper.fromEventMessage(eventMessage);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.getEventId()).isEqualTo(EVENT_ID);
        softly.assertThat(result.getCategory()).isEqualTo(CATEGORY);
        softly.assertThat(result.getSubCategory()).isEqualTo(SUB_CATEGORY);
        softly.assertThat(result.getName()).isEqualTo(EVENT_NAME);
        softly.assertThat(result.getStartTime()).isEqualTo(START_TIME);
        softly.assertThat(result.getDisplayed()).isEqualTo(DISPLAYED);
        softly.assertThat(result.getSuspended()).isEqualTo(SUSPENDED);
        softly.assertThat(result.getMarkets()).isEmpty();
        softly.assertAll();
    }

    @Test
    public void fromMarketMessage_returnExpectedMarket() {

        MarketMessage message = new MarketMessage(MSG_ID, OPERATION, TYPE, TIMESTAMP, EVENT_ID, MARKET_ID, MARKET_NAME, DISPLAYED, SUSPENDED);

        Market result = mapper.fromMarketMessage(message);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.getEventId()).isEqualTo(EVENT_ID);
        softly.assertThat(result.getMarketId()).isEqualTo(MARKET_ID);
        softly.assertThat(result.getName()).isEqualTo(MARKET_NAME);
        softly.assertThat(result.getDisplayed()).isEqualTo(DISPLAYED);
        softly.assertThat(result.getSuspended()).isEqualTo(SUSPENDED);
        softly.assertThat(result.getOutcomes()).isEmpty();
        softly.assertAll();
    }

    @Test
    public void fromOutcomeMessage() {

        OutcomeMessage message = new OutcomeMessage(MSG_ID, OPERATION, TYPE, TIMESTAMP, MARKET_ID, OUTCOME_ID, OUTCOME_NAME, PRICE, DISPLAYED, SUSPENDED);

        Outcome result = mapper.fromOutcomeMessage(message);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.getMarketId()).isEqualTo(MARKET_ID);
        softly.assertThat(result.getOutcomeId()).isEqualTo(OUTCOME_ID);
        softly.assertThat(result.getName()).isEqualTo(OUTCOME_NAME);
        softly.assertThat(result.getPrice()).isEqualTo(PRICE);
        softly.assertThat(result.getDisplayed()).isEqualTo(DISPLAYED);
        softly.assertThat(result.getSuspended()).isEqualTo(SUSPENDED);
        softly.assertAll();
    }
}