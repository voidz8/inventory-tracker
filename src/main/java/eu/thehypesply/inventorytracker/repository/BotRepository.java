package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.Bot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BotRepository extends MongoRepository <Bot, String>{

    List<Bot> findAllByBotName(String botName);
}
