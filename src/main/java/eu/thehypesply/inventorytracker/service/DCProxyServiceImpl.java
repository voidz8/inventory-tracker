package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.exception.ProxyNotFound;
import eu.thehypesply.inventorytracker.model.DCProxy;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.repository.DCProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DCProxyServiceImpl implements DCProxyService {

    @Autowired
    private DCProxyRepository dcProxyRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public List<DCProxy> getAllDCProxies() {
        return dcProxyRepository.findAll();
    }

    @Override
    public Optional<DCProxy> getDCProxy(long id) {
        if (!dcProxyRepository.existsById(id)) {
            throw new ProxyNotFound();
        }
        return dcProxyRepository.findById(id);
    }

    @Override
    public String createDCProxy(DCProxy dcProxy) {
        DCProxy newProxy = dcProxyRepository.save(dcProxy);
        return newProxy.getProxyCompany();
    }

    @Override
    public void updateDCProxy(long id, Map<String, Object> fields) throws ParseException {
        if (!dcProxyRepository.existsById(id)) {
            throw new ProxyNotFound();
        }
        DCProxy proxy = dcProxyRepository.findById(id).get();
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
        dcProxyRepository.save(proxy);
    }

    @Override
    public void deleteDCProxy(long id) {
        dcProxyRepository.deleteById(id);
    }

    @Override
    public long spendOnDcProxies() {
        List<DCProxy> dcProxies = dcProxyRepository.findAll();
        long total = 0;
        for (DCProxy proxy : dcProxies) {
            long value = proxy.getPrice();
            total = total + value;
        }
        return total;
    }

    @Override
    public void uploadInvoice(long id, Image image) {
        if (!dcProxyRepository.existsById(id)) {
            throw new ProxyNotFound();
        }
        DCProxy dcProxy = dcProxyRepository.findById(id).get();
        dcProxy.setInvoice(image);
        dcProxyRepository.save(dcProxy);
    }

    @Override
    public void deleteInvoice(long id) {
        if (!dcProxyRepository.existsById(id)) {
            throw new ProxyNotFound();
        }
        DCProxy dcProxy = dcProxyRepository.findById(id).get();
        String imageId = dcProxy.getInvoice().getId();
        dcProxy.setInvoice(null);
        imageService.deleteImage(imageId);
        dcProxyRepository.save(dcProxy);
    }
}
