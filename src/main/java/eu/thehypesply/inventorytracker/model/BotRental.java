package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document
public class BotRental {

    @Id
    @Field
    private String id;

    @Field
    private LocalDate rentalDate;

    @Field
    private LocalDate rentalExpiry;

    @Field
    private long price;

    @DBRef
    private Bot bot;


}
