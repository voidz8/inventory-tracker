package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Data
public class Image {

    @Id
    private String id;

    @Field
    private String fileName;

    @Field
    private String fileType;

    @Field
    private byte[] data;

}
