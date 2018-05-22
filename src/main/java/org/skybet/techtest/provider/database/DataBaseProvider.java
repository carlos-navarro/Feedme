package org.skybet.techtest.provider.database;

import org.skybet.techtest.model.Event;

import java.util.Optional;

public interface DataBaseProvider {

    void save (Event event);

    Optional<Event> findOneEvent(String eventId);

    Optional<Event> findEventContainingMarket(String marketId);

}
