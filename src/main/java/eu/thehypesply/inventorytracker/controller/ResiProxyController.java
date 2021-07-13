package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.ResiProxy;
import eu.thehypesply.inventorytracker.service.ImageService;
import eu.thehypesply.inventorytracker.service.ResiProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "resiproxy")
public class ResiProxyController {

    @Autowired
    private ResiProxyService resiProxyService;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllProxies() {
        return new ResponseEntity<>(resiProxyService.getAllProxies(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getProxy(@PathVariable(value = "id") long id) {
        return new ResponseEntity<>(resiProxyService.getProxy(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createProxy(@RequestBody ResiProxy resiProxy) {
        String newProxy = resiProxyService.createProxy(resiProxy);
        return new ResponseEntity<>("Successfully added new proxy " + newProxy, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateProxy(@PathVariable(value = "id") long id, @RequestBody Map<String, Object> fields) throws ParseException {
        resiProxyService.updateProxy(id, fields);
        return new ResponseEntity<>("Successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteProxy(@PathVariable(value = "id") long id) {
        resiProxyService.deleteProxy(id);
        return new ResponseEntity<>("Successfully deleted.", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/invested")
    public ResponseEntity<Object> spendOnResiProxies() {
        return new ResponseEntity<>("You have spend $" + resiProxyService.spendOnProxies() + " on residential proxies.", HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/invoice")
    public ResponseEntity<Object> addInvoice(@PathVariable(value = "id") long id, @RequestParam("file") MultipartFile file) {
        Image image = imageService.storeImage(file);
        resiProxyService.uploadInvoice(id, image);
        return new ResponseEntity<>("Successfully uploaded invoice", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/invoice")
    public ResponseEntity<Object> deleteInvoice(@PathVariable(value = "id") long id) {
        resiProxyService.deleteInvoice(id);
        return new ResponseEntity<>("Successfully deleted invoice.", HttpStatus.NO_CONTENT);
    }
}
