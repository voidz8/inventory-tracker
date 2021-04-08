package eu.thehypesply.inventorytracker.repository;

import eu.thehypesply.inventorytracker.model.DCProxy;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DCProxyRepository extends MongoRepository<DCProxy, String>{

    boolean existsByProxyCompany(String proxyCompany);
    List<DCProxy> findByProxyCompany(String proxyCompany);
}
