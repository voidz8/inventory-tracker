package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.BotRental;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BotRentalRepository extends MongoRepository<BotRental, String> {
}
