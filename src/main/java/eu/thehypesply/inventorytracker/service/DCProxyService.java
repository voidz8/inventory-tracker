package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.DCProxy;
import eu.thehypesply.inventorytracker.model.Image;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DCProxyService {
    List<DCProxy> getAllDCProxies();
    Optional<DCProxy> getDCProxy(String id);
    String createDCProxy(DCProxy dcProxy);
    void updateDCProxy(String id, Map<String, Object> fields) throws ParseException;
    void deleteDCProxy(String id);
    long spendOnDcProxies();
    void uploadInvoice(String id, Image image);
    void deleteInvoice(String id);
}
