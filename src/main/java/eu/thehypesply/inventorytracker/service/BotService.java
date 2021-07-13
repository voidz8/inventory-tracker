package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.model.Bot;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BotService {

    List<Bot> getAllBots();

    Optional<Bot> getBot(long id);

    String createBot(Bot bot);

    void deleteBot(long id);

    void updateBot(long id, Map<String, Object> fields);

    long totalBotInvestment();

    long totalBotSales();

    long totalBalance();

    List<DataDto> getBotData(Authentication auth);
}
