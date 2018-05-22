package org.skybet.techtest.provider.database.mongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.skybet.techtest.model.Event;
import org.skybet.techtest.provider.database.DataBaseProvider;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MongoProviderTest {

    public static final String EVENT_ID = "event-id";
    public static final String MARKET_ID = "market-id";

    @Mock
    private MongoEventRepository repository;

    @Mock
    private Event mEvent;

    private DataBaseProvider provider;

    @Before
    public void setUp() throws Exception {
        provider = new MongoProvider(repository);
    }

    @Test
    public void saveEvent_callRepositorySave() {

        provider.save(mEvent);

        verify(repository).save(mEvent);
    }

    @Test
    public void findOneEvent_ReturnEvent() {

        when(repository.findByEventId(EVENT_ID)).thenReturn(mEvent);

        Optional<Event> expected = provider.findOneEvent(EVENT_ID);

        assertThat(expected.isPresent()).isTrue();
    }

    @Test
    public void EventIdNotContainedInDatabase_ReturnEmptyEvent() {

        when(repository.findByEventId(EVENT_ID)).thenReturn(null);

        Optional<Event> expected = provider.findOneEvent(EVENT_ID);

        assertThat(expected.isPresent()).isFalse();
    }

    @Test
    public void findEventContainingMarket_ReturnEvent() {

        when(repository.findByMarketsMarketId(MARKET_ID)).thenReturn(singletonList(mEvent));

        Optional<Event> expected = provider.findEventContainingMarket(MARKET_ID);

        assertThat(expected.isPresent()).isTrue();
    }

    @Test
    public void findNonExistingMarket_ReturnEmptyEvent() {

        when(repository.findByMarketsMarketId(MARKET_ID)).thenReturn(emptyList());

        Optional<Event> expected = provider.findEventContainingMarket(MARKET_ID);

        assertThat(expected.isPresent()).isFalse();
    }
}