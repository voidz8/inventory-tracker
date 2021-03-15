package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.DCProxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DCProxyRepository extends MongoRepository<DCProxy, String>{
}
