package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.Clothing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingRepository extends MongoRepository<Clothing, String> {
}
