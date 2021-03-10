package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
public class Bot {

    @Id
    private String botName;

    @Field
    private long priceBought;

    @Field
    private long priceSold;

    @Field
    private long value;

    @Field
    private long rentalIncome;

    public Bot() {
    }
}
