package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.Sneaker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakerRepository extends MongoRepository<Sneaker, String> {
}
