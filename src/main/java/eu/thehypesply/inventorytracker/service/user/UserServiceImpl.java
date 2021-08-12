package eu.thehypesply.inventorytracker.service.user;

import eu.thehypesply.inventorytracker.dto.AccountDto;
import eu.thehypesply.inventorytracker.model.User;
import eu.thehypesply.inventorytracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

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

    @Override
    public AccountDto getAccountInfo(Authentication auth) {
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            String username = userDetails.getUsername();
            User user = getUserByUsername(username);
            return new AccountDto(user.getId(), username, userDetails.getEmail());
    }

    @Override
    public ResponseEntity<Object> updateUser(Authentication auth, User userInfo) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String username = userDetails.getUsername();
        User user = getUserByUsername(username);
        if (userRepository.existsByUsername(userInfo.getUsername())){
            return ResponseEntity.badRequest().body("Username already exists");
        } else {
            user.setEmail(userInfo.getEmail());
            user.setPassword(encoder.encode(userInfo.getPassword()));
            user.setUsername(userInfo.getUsername());
            userRepository.save(user);
            return ResponseEntity.ok("Succesfully updated user");}
    }
}
