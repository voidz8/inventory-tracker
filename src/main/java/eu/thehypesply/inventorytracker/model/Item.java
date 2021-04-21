package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document
public class Item {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private long priceBought;

    @Field
    private long priceSold;

    @Field
    private LocalDate dateBought;

    @Field
    private LocalDate dateSold;

    @Field
    private Image invoice;

    public Item() {
    }
}
