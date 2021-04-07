package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.repository.DCProxyRepository;
import eu.thehypesply.inventorytracker.service.DCProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dcproxy")
public class DCProxyController {

    @Autowired
    private DCProxyService dcProxyService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllDcProxies(){
        return new ResponseEntity<>(dcProxyService.getAllDCProxies(), HttpStatus.OK);
    }

//    @GetMapping(value = "/{id}")
}
