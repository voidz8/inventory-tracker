package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image storeImage(MultipartFile file);
    void deleteImage(String id);
}