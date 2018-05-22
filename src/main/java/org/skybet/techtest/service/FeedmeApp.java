package org.skybet.techtest.service;


import org.skybet.techtest.Config.DataProviderConfig;
import org.skybet.techtest.model.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@Component
public class FeedmeApp implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(FeedmeApp.class);

    private final StreamLineMapper mapper;
    private final FeedSaver feedSaver;
    private final DataProviderConfig providerConfig;

    @Autowired
    public FeedmeApp(StreamLineMapper mapper, FeedSaver feedSaver, DataProviderConfig providerConfig) {
        this.mapper = mapper;
        this.feedSaver = feedSaver;
        this.providerConfig = providerConfig;
    }

    @Override
    public void run(String... strings) throws Exception {
        main(strings);
    }

    private void main(String[] strings) {

        try (Socket clientSocket = new Socket(providerConfig.getHost(), providerConfig.getPort());
             BufferedReader bufferedReader =
                     new BufferedReader(
                             new InputStreamReader(
                                     clientSocket.getInputStream()
                             )
                     )
        ) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                Message message = mapper.toMessage(line);
                log.info(mapper.toJson(message));
                feedSaver.save(message);
            }

        } catch (IOException e) {
            log.error("Unexpected error : {}", e.getCause());
        }
    }
}