package eu.thehypesply.inventorytracker.service.user;

import eu.thehypesply.inventorytracker.dto.AccountDto;
import eu.thehypesply.inventorytracker.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserService {

    Long getUserIdFromUsername(String username);
    User getUserByUsername(String username);
    AccountDto getAccountInfo(Authentication auth);
    ResponseEntity<Object> updateUser(Authentication auth, User userInfo);
}
