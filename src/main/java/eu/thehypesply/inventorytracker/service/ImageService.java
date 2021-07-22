package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void deleteImage(String id);
    Image saveImage(MultipartFile imageFile) throws IOException;
}
