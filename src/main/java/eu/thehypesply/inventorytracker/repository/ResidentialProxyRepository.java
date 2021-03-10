package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.ResidentialProxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentialProxyRepository extends MongoRepository<ResidentialProxy, String> {
}
