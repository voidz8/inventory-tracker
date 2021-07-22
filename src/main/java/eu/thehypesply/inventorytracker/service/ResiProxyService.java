package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.ResiProxy;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ResiProxyService {

    List<ResiProxy> getAllProxies();

    Optional<ResiProxy> getProxy(long id);

    String createProxy(ResiProxy resiProxy);

    void updateProxy(long id, Map<String, Object> fields) throws ParseException;

    void deleteProxy(long id);

    long spendOnProxies();
}
