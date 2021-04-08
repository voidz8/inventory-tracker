package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.ResiProxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResiProxyRepository extends MongoRepository<ResiProxy, String> {

    boolean existsByProxyCompany(String proxyCompany);
    List<ResiProxy> findByProxyCompany(String proxyCompany);

}
