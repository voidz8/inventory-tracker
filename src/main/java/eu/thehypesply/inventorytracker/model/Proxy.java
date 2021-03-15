package eu.thehypesply.inventorytracker.model;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;

@Data
public abstract class Proxy {

    @Field
    private String proxyCompany;

    @Field
    private long price;

    @Field
    private LocalDate expiryDate;

    @Field
    private byte[] invoice;

    public Proxy() {
    }
}
