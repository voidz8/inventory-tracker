package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.DCProxy;
import eu.thehypesply.inventorytracker.model.Image;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DCProxyService {
    List<DCProxy> getAllDCProxies();

    Optional<DCProxy> getDCProxy(long id);

    String createDCProxy(DCProxy dcProxy);

    void updateDCProxy(long id, Map<String, Object> fields) throws ParseException;

    void deleteDCProxy(long id);

    long spendOnDcProxies();
}
