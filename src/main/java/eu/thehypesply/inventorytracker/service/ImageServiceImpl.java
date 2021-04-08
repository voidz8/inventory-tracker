package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.exception.ImageStorageException;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image storeImage(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw new ImageStorageException("Filename contains invalid path sequence " + fileName);
            }
            Image image = new Image(fileName, file.getContentType(), file.getBytes());
            return imageRepository.save(image);
        } catch (IOException e){
            throw new ImageStorageException("Could not store file " + fileName, e);
        }
    }

    @Override
    public void deleteImage(String id) {
        imageRepository.deleteById(id);
    }
}
