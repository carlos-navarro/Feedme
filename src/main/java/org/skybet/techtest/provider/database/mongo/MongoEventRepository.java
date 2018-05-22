package org.skybet.techtest.provider.database.mongo;

import org.skybet.techtest.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoEventRepository extends MongoRepository<Event, String> {

    Event findByEventId(String eventId);

    List<Event> findByMarketsMarketId(String marketId);

}
