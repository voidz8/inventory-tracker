package eu.thehypesply.inventorytracker.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.annotation.Documented;
import java.time.LocalDate;

@Data
public abstract class Proxy {

    @Id
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
