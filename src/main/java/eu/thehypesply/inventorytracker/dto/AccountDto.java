package eu.thehypesply.inventorytracker.dto;

import lombok.Data;

@Data
public class AccountDto {

    private Long id;
    private String username;
    private String email;

    public AccountDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }


}
