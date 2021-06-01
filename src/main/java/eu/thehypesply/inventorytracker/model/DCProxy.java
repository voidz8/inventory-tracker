package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "DatacenterProxies")
public class DCProxy extends Proxy {

    @Id
    private String id;

    @Field
    private long amount;
}
