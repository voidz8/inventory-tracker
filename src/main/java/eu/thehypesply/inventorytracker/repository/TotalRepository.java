package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.Total;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalRepository extends MongoRepository<Total, String> {
}
