package eu.thehypesply.inventorytracker.payload.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignUpResponse {

    private String message;
    private boolean success;

    public SignUpResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public SignUpResponse(String message) {
        this.message = message;
    }
}
