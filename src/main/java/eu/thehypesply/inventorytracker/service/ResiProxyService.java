package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.ResiProxy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ResiProxyService {

    List<ResiProxy> getAllProxies();
    Optional<ResiProxy> getProxy(String id);
    List<ResiProxy> getProxiesByCompany(String proxyCompany);
    String createProxy(ResiProxy resiProxy);
    void updateProxy(String id, Map<String, Object> fields);
    void deleteProxy(String id);
    long spendOnProxies();
}
