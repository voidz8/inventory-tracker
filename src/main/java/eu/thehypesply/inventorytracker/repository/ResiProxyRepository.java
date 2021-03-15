package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.ResiProxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResiProxyRepository extends MongoRepository<ResiProxy, String> {
}
