package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.service.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/sneakers")
public class SneakerController {

    @Autowired
    private SneakerService sneakerService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllSneakers(){
        return new ResponseEntity<>(sneakerService.getAllSneakers(), HttpStatus.OK);
    }
    @GetMapping(value = "/{pid}")
    public ResponseEntity<Object> getSneaker(@PathVariable(value = "pid") String pid){
        return new ResponseEntity<>(sneakerService.getSneaker(pid), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createSneaker(@RequestBody Sneaker sneaker){
    String newSneaker = sneakerService.createSneaker(sneaker);
    return new ResponseEntity<>("Succesfully created pid: " + newSneaker, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{pid}")
    public ResponseEntity<Object> deleteSneaker(@PathVariable(value = "pid")String pid){
        sneakerService.deleteSneaker(pid);
        return new ResponseEntity<>("Successfully deleted.",HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{pid}")
    public ResponseEntity<Object> updateSneaker(@PathVariable(value = "pid")String pid, @RequestBody Map<String, Object> fields){
        sneakerService.updateSneaker(pid,fields);
        return new ResponseEntity<>("Succesfully updated sneaker with pid: " + pid+ ".", HttpStatus.OK);
    }

}
