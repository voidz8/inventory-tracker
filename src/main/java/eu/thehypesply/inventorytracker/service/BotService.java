package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Bot;
import eu.thehypesply.inventorytracker.model.BotRental;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BotService {

    List<Bot> getAllBots();
    List<Bot> getBots(String botName);
    String createBot(Bot bot);
    void deleteBot(String id);
    void updateBot(String id, Map<String, Object> fields);
    long totalBotInvestment();
    long totalBotSales();
    long totalBalance();
}
