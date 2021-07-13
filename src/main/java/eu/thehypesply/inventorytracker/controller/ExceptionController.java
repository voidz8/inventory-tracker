package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.exception.SneakerNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@CrossOrigin(origins = "*")
public class ExceptionController {

    @ExceptionHandler(value = {SneakerNotFound.class})
    public ResponseEntity<Object> exception(SneakerNotFound e){
        return ResponseEntity.notFound().build();
    }


}
