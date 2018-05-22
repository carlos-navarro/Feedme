package org.skybet.techtest.provider.database.mongo;

import org.skybet.techtest.model.Event;
import org.skybet.techtest.provider.database.DataBaseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoProvider implements DataBaseProvider {

    private final MongoEventRepository repository;

    @Autowired
    public MongoProvider(MongoEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Event event) {
        repository.save(event);
    }

    @Override
    public Optional<Event> findOneEvent(String eventId) {
        return Optional.ofNullable(repository.findByEventId(eventId));
    }

    @Override
    public Optional<Event> findEventContainingMarket(String marketId) {
        return repository.findByMarketsMarketId(marketId).stream()
                .findFirst();
    }
}