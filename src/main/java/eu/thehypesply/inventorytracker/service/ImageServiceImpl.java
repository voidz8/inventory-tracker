package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public void deleteImage(String id) {
        imageRepository.deleteById(id);
    }


    @Override
    public Image saveImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setData(imageFile.getBytes());
        image.setFileName(imageFile.getOriginalFilename());
        image.setFileType(imageFile.getContentType());
        imageRepository.save(image);
        return image;
    }
}
