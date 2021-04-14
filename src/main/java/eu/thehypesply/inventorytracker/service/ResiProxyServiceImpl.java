package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.exception.ProxyNotFound;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.ResiProxy;
import eu.thehypesply.inventorytracker.repository.ResiProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResiProxyServiceImpl implements ResiProxyService{

    @Autowired
    private ResiProxyRepository resiProxyRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public List<ResiProxy> getAllProxies() {
        return resiProxyRepository.findAll();
    }

    @Override
    public Optional<ResiProxy> getProxy(String id) {
        if (!resiProxyRepository.existsById(id)){throw new ProxyNotFound();}
        return resiProxyRepository.findById(id);
    }

    @Override
    public List<ResiProxy> getProxiesByCompany(String proxyCompany) {
        if (!resiProxyRepository.existsByProxyCompany(proxyCompany)){throw new ProxyNotFound("No proxies found for this provider");}
        return resiProxyRepository.findByProxyCompany(proxyCompany);
    }

    @Override
    public String createProxy(ResiProxy resiProxy) {
        ResiProxy newProxy = resiProxyRepository.save(resiProxy);
        return newProxy.getProxyCompany();
    }

    @Override
    public void updateProxy(String id, Map<String, Object> fields) throws ParseException {
    if (!resiProxyRepository.existsById(id)){throw new ProxyNotFound();}
    ResiProxy proxy = resiProxyRepository.findById(id).get();
    for (String field : fields.keySet()){
        switch (field){
            case "amount":
                proxy.setAmount((String) fields.get(field));
                break;
            case "proxyCompany":
                proxy.setProxyCompany((String) fields.get(field));
                break;
            case "price":
                proxy.setPrice((Long) fields.get(field));
                break;
            case "expiryDate":
                Date newExpiryDate = new SimpleDateFormat("dd-MM-yyy").parse(fields.get(field).toString());
                newExpiryDate.setTime(newExpiryDate.getTime()+3600000);
                proxy.setExpiryDate(newExpiryDate);
                break;
        }
    }
    resiProxyRepository.save(proxy);
    }

    @Override
    public void deleteProxy(String id) {
    resiProxyRepository.deleteById(id);
    }

    @Override
    public long spendOnProxies() {
        List<ResiProxy> proxies = resiProxyRepository.findAll();
        long total = 0;
        for (ResiProxy proxy : proxies){
            long value = proxy.getPrice();
            total = total + value;
        }
        return total;
    }

    @Override
    public void uploadInvoice(String id, Image image) {
        if (!resiProxyRepository.existsById(id)){throw new ProxyNotFound();}
        ResiProxy resiProxy = resiProxyRepository.findById(id).get();
        resiProxy.setInvoice(image);
        resiProxyRepository.save(resiProxy);
    }

    @Override
    public void deleteInvoice(String id) {
        if (!resiProxyRepository.existsById(id)){throw new ProxyNotFound();}
        ResiProxy resiProxy = resiProxyRepository.findById(id).get();
        String imageId = resiProxy.getInvoice().getId();
        resiProxy.setInvoice(null);
        imageService.deleteImage(imageId);
        resiProxyRepository.save(resiProxy);
    }


}
