package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.Proxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProxyRepository extends MongoRepository<Proxy, String> {
}
