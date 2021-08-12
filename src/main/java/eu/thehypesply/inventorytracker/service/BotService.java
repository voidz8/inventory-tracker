package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.model.Bot;
import eu.thehypesply.inventorytracker.model.Image;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BotService {

    List<Bot> getAllBots(Authentication auth);

    Optional<Bot> getBot(long id);

    List<Bot> getAllSoldBots(Authentication auth);

    String createBot(Bot bot, Authentication auth, Image image);

    void deleteBot(long id);

    void updateBot(long id, Map<String, Object> fields);

    long totalBotInvestment();

    long totalBotSales();

    long totalBalance();

    void sellBot(long id, Long priceSold);

    List<DataDto> getBotData(Authentication auth);
}
