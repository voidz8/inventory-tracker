package eu.thehypesply.inventorytracker.service.user;

import eu.thehypesply.inventorytracker.model.User;

public interface UserService {

    Long getUserIdFromUsername(String username);
    User getUserByUsername(String username);
}
