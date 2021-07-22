package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.model.User;
import eu.thehypesply.inventorytracker.service.ImageService;
import eu.thehypesply.inventorytracker.service.SneakerService;
import eu.thehypesply.inventorytracker.service.user.UserDetailsImpl;
import eu.thehypesply.inventorytracker.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/sneakers")
public class SneakerController {

    @Autowired
    private SneakerService sneakerService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllSneakers(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.getUserByUsername(username);
        return new ResponseEntity<>(sneakerService.getAllSneakers(user), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getSneaker(@PathVariable(value = "id") long id) {
        return new ResponseEntity<>(sneakerService.getSneaker(id), HttpStatus.OK);
    }


    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity<Object> createSneakerManual(@ModelAttribute Sneaker sneaker, Authentication authentication, @ModelAttribute MultipartFile photo) throws IOException {
        Image image = imageService.saveImage(photo);
        Sneaker newSneaker = sneakerService.createSneakerManual(sneaker, authentication, image);
        imageService.saveImage(photo);
        return new ResponseEntity<>("Successfully created: " + newSneaker.getSneakerName(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteSneaker(@PathVariable(value = "id") long id) {
        sneakerService.deleteSneaker(id);
        return new ResponseEntity<>("Successfully deleted.", HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateSneaker(@PathVariable(value = "id") long id, @RequestBody Map<String, Object> fields) {
        sneakerService.updateSneaker(id, fields);
        return new ResponseEntity<>("Succesfully updated: " + sneakerService.getSneaker(id).get().getSneakerName() + ".", HttpStatus.OK);
    }

    @GetMapping(value = "/invested")
    public ResponseEntity<Object> getTotalInvested() {
        return new ResponseEntity<>("You have $" + sneakerService.getTotalBought() + " invested in sneakers.", HttpStatus.OK);
    }

    @GetMapping(value = "/sales")
    public ResponseEntity<Object> getTotalSold() {
        return new ResponseEntity<>("Your total in sales is $" + sneakerService.getTotalSold(), HttpStatus.OK);
    }

    @GetMapping(value = "/balance")
    public ResponseEntity<Object> getBalance() {
        return new ResponseEntity<>("Your balance is $" + sneakerService.getBalance(), HttpStatus.OK);
    }

    @GetMapping(value = "data")
    public ResponseEntity<Object> getSneakerData(Authentication auth) {
        return new ResponseEntity<>(sneakerService.getSneakerData(auth), HttpStatus.OK);
    }

    @GetMapping(value = "/sold")
    public ResponseEntity<Object> getSoldSneakers(Authentication auth) {
        return new ResponseEntity<>(sneakerService.getAllSoldSneakers(auth), HttpStatus.OK);
    }

    @PostMapping(value = "/sell/{id}")
    public ResponseEntity<Object> sellSneaker(@PathVariable(value = "id") long id, int priceSold){
        sneakerService.sold(id,priceSold);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
