package eu.thehypesply.inventorytracker.service.auth;

import eu.thehypesply.inventorytracker.model.ERole;
import eu.thehypesply.inventorytracker.model.Role;
import eu.thehypesply.inventorytracker.model.User;
import eu.thehypesply.inventorytracker.payload.request.LoginRequest;
import eu.thehypesply.inventorytracker.payload.request.SignupRequest;
import eu.thehypesply.inventorytracker.payload.response.JwtResponse;
import eu.thehypesply.inventorytracker.payload.response.SignUpResponse;
import eu.thehypesply.inventorytracker.repository.RoleRepository;
import eu.thehypesply.inventorytracker.repository.UserRepository;
import eu.thehypesply.inventorytracker.service.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class AuthService {

    private static final String ROLE_NOT_FOUND_ERROR = "Error: Role not found.";

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder) {
        this.encoder = passwordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<SignUpResponse> registerUser(@Valid SignupRequest signupRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signupRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new SignUpResponse("Error: Username already exists."));
        }
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signupRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new SignUpResponse("Error: Email already exists"));
        }

        //Create new user account
        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(()-> new RuntimeException(ROLE_NOT_FOUND_ERROR));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("ADMIN".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new SignUpResponse("User successfully registered."));
    }

    public ResponseEntity<JwtResponse> authenticateUser(@Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getEmail(), roles));
    }
}
