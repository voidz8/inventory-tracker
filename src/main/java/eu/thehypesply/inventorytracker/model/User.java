package eu.thehypesply.inventorytracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Document(collection = "app_user")
@Data
public class User {

    @Id
    @Field
    private long id;

    @Field
    private String username;

    @Field
    private String email;

    @Field
    private String password;

    @Field
    private Set<Role> roles;


}
