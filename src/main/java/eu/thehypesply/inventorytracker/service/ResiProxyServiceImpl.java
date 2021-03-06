package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.exception.ProxyNotFound;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.ResiProxy;
import eu.thehypesply.inventorytracker.repository.ResiProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResiProxyServiceImpl implements ResiProxyService {

    @Autowired
    private ResiProxyRepository resiProxyRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public List<ResiProxy> getAllProxies() {
        return resiProxyRepository.findAll();
    }

    @Override
    public Optional<ResiProxy> getProxy(long id) {
        if (!resiProxyRepository.existsById(id)) {
            throw new ProxyNotFound();
        }
        return resiProxyRepository.findById(id);
    }

    @Override
    public String createProxy(ResiProxy resiProxy) {
        ResiProxy newProxy = resiProxyRepository.save(resiProxy);
        return newProxy.getProxyCompany();
    }

    @Override
    public void updateProxy(long id, Map<String, Object> fields) throws ParseException {
        if (!resiProxyRepository.existsById(id)) {
            throw new ProxyNotFound();
        }
        ResiProxy proxy = resiProxyRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field) {
                case "amount":
                    proxy.setAmount((Integer) fields.get(field));
                    break;
                case "proxyCompany":
                    proxy.setProxyCompany((String) fields.get(field));
                    break;
                case "price":
                    proxy.setPrice((Integer) fields.get(field));
                    break;
                case "expiryDate":
                    Date newExpiryDate = new SimpleDateFormat("dd-MM-yyy").parse(fields.get(field).toString());
                    newExpiryDate.setTime(newExpiryDate.getTime() + 3600000);
                    proxy.setExpiryDate(newExpiryDate);
                    break;
            }
        }
        resiProxyRepository.save(proxy);
    }

    @Override
    public void deleteProxy(long id) {
        resiProxyRepository.deleteById(id);
    }

    @Override
    public long spendOnProxies() {
        List<ResiProxy> proxies = resiProxyRepository.findAll();
        long total = 0;
        for (ResiProxy proxy : proxies) {
            long value = proxy.getPrice();
            total = total + value;
        }
        return total;
    }
}
