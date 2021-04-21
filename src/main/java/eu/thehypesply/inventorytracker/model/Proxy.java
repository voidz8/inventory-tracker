package eu.thehypesply.inventorytracker.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;

@Data
public abstract class Proxy {

    @Field
    private String proxyCompany;

    @Field
    private long price;

    @Field
    private LocalDate dateBought;

    @JsonDeserialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Field
    private Date expiryDate;

    @Field
    private Image invoice;

    public Proxy() {
    }
}
