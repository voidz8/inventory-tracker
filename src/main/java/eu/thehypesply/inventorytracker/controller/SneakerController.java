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
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping(value = "", consumes = {"multipartfile/form-data"})
    public ResponseEntity<Object> createSneakerManual(@RequestBody Sneaker sneaker, Authentication authentication, @ModelAttribute MultipartFile photo, @ModelAttribute MultipartFile invoice) {
        Image uploadedPhoto = imageService.storeImage(photo);
        Image uploadedInvoice = imageService.storeImage(invoice);
        String newSneaker = sneakerService.createSneakerManual(sneaker, authentication, uploadedPhoto, uploadedInvoice);
        return new ResponseEntity<>("Successfully created: " + newSneaker, HttpStatus.CREATED);
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

    @PostMapping(value = "/{id}/invoice")
    public ResponseEntity<Object> addInvoice(@PathVariable(value = "id") long id, @RequestParam("file") MultipartFile file) {
        Image image = imageService.storeImage(file);
        sneakerService.uploadInvoice(id, image);
        return new ResponseEntity<>("Successfully uploaded invoice.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/invoice")
    public ResponseEntity<Object> deleteInvoice(@PathVariable(value = "id") long id) {
        sneakerService.deleteInvoice(id);
        return new ResponseEntity<>("Successfully deleted invoice.", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "data")
    public ResponseEntity<Object> getSneakerData(Authentication auth) {
        return new ResponseEntity<>(sneakerService.getSneakerData(auth), HttpStatus.OK);
    }

//    @PostMapping(value = "/{id}/picture")
//    public ResponseEntity<Object> addPicture(@PathVariable(value = "id") String id, @RequestParam("file") MultipartFile file){
//        Image image = imageService.storeImage(file);
//        sneakerService.
//    }

}
