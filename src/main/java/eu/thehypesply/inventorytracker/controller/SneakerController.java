package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.service.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ClientInfoStatus;

@RestController
@RequestMapping(value = "/sneakers")
public class SneakerController {

    @Autowired
    private SneakerService sneakerService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllSneakers(){
        return new ResponseEntity<>(sneakerService.getAllSneakers(), HttpStatus.OK);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Object> createSneaker(@RequestBody Sneaker sneaker){
    String newSneaker = sneakerService.createSneaker(sneaker);
    return new ResponseEntity<>("Succesfully added pid: " + newSneaker + " to the database.", HttpStatus.CREATED);
    }
}
