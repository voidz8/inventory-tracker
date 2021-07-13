package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.BotRental;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BotRentalService {

    List<BotRental> getAllBotRentals();
    Optional<BotRental> getBotRental(long id);
    String createBotRental(BotRental botRental);
    void deleteBotRental(long id);
    void updateBotRental(long id, Map<String, Object> fields) throws ParseException;
    long rentalIncome();
}
