package eu.thehypesply.inventorytracker.service.user;

import eu.thehypesply.inventorytracker.model.User;
import eu.thehypesply.inventorytracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Long getUserIdFromUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found" + username));
        return user.getId();
    }

    @Override
    public User getUserByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found" + username));
        return user;
    }
}
