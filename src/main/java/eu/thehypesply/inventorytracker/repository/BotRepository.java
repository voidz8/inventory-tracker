package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.Bot;
import eu.thehypesply.inventorytracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BotRepository extends JpaRepository<Bot, Long> {
    List<Bot> findAllByBotName(String botName);
    List<Bot> findAllByUser(User user);
    List<Bot> findAllByUserAndPriceSoldIsNotNull(User user);
}
