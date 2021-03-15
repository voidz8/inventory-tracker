package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Sneakers")
@Data
public class Sneaker {

    @Field
    @Id
    private String pid;

    @Field
    private String sneakerName;

    @Field
    private long size;

    @Field
    private long priceBought;

    @Field
    private long salePrice;


    public Sneaker() {
    }
}
