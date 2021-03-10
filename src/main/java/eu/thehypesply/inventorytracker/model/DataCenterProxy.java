package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document(collection = "DatacenterProxies")
public class DataCenterProxy extends Proxy {

    @Field
    private Long amount;
}
