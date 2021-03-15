package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Bot;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BotService {

    List<Bot> getAllBots();
    Optional<Bot> getBot(String botName);
    String createBot(Bot bot);
    void deleteBot(String botName);
    void updateBot(String botName, Map<String, Object> fields);
}
