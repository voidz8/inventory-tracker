package eu.thehypesply.inventorytracker.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 4, max = 20)
    @Getter
    @Setter
    private String username;

    @NotBlank
    @Getter
    @Setter
    private String email;

    @NotBlank
    @Size(min = 8,max = 30)
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private Set<String> role;
}
