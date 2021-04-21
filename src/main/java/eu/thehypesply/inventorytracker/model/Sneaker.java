package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "Sneakers")
@Data
public class Sneaker {

    @Id
    private String id;

    @Field
    private String pid;

    @Field
    private String sneakerName;

    @Field
    private long size;

    @Field
    private long priceBought;

    @Field
    private long salePrice;

    @Field
    private byte[] photo;

    @Field
    private Image invoice;

    @Field
    private LocalDate dateBought;

    @Field
    private LocalDate dateSold;

}
