package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.exception.ProxyNotFound;
import eu.thehypesply.inventorytracker.model.DCProxy;
import eu.thehypesply.inventorytracker.repository.DCProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DCProxyServiceImpl implements DCProxyService {

    @Autowired
    private DCProxyRepository dcProxyRepository;

    @Override
    public List<DCProxy> getAllDCProxies() {
        return dcProxyRepository.findAll();
    }

    @Override
    public Optional<DCProxy> getDCProxy(String id) {
        if (!dcProxyRepository.existsById(id)){throw new ProxyNotFound();}
        return dcProxyRepository.findById(id);
    }

    @Override
    public String createDCProxy(DCProxy dcProxy) {
        DCProxy newProxy = dcProxyRepository.save(dcProxy);
        return newProxy.getProxyCompany();
    }

    @Override
    public void updateDCProxy(String id, Map<String, Object> fields) {
    if (!dcProxyRepository.existsById(id)){throw new ProxyNotFound();}
    DCProxy proxy = dcProxyRepository.findById(id).get();
    for (String field : fields.keySet()){
        switch (field){
            case "amount":
                proxy.setAmount((Long) fields.get(field));
                break;
            case "proxyCompany":
                proxy.setProxyCompany((String) fields.get(field));
                break;
            case "price":
                proxy.setPrice((Long) fields.get(field));
                break;
            case "expiryDate":
                LocalDate newDate = LocalDate.parse(fields.get(field).toString());
                proxy.setExpiryDate(newDate);
                break;
        }
    }
    dcProxyRepository.save(proxy);
    }

    @Override
    public void deleteDCProxy(String id) {
    dcProxyRepository.deleteById(id);
    }
}