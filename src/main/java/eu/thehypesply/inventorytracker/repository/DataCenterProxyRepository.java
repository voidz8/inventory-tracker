package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.DataCenterProxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCenterProxyRepository extends MongoRepository<DataCenterProxy, String>{
}
