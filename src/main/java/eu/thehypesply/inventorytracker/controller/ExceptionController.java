package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.exception.SneakerNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {SneakerNotFound.class})
    public ResponseEntity<Object> exception(SneakerNotFound e){
        return ResponseEntity.notFound().build();
    }


}
