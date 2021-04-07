package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.BotRental;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BotRentalService {

    List<BotRental> getAllBotRentals();
    Optional<BotRental> getBotRental(String id);
    String createBotRental(BotRental botRental);
    void deleteBotRental(String id);
    void updateBotRental(String id, Map<String, Object> fields);
    long rentalIncome();
}
