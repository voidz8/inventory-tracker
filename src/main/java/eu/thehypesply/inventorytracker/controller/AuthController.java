package eu.thehypesply.inventorytracker.controller;

import eu.thehypesply.inventorytracker.dto.AccountDto;
import eu.thehypesply.inventorytracker.payload.request.LoginRequest;
import eu.thehypesply.inventorytracker.payload.request.SignupRequest;
import eu.thehypesply.inventorytracker.payload.response.JwtResponse;
import eu.thehypesply.inventorytracker.payload.response.SignUpResponse;
import eu.thehypesply.inventorytracker.service.auth.AuthService;
import eu.thehypesply.inventorytracker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest){
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("signup")
    public ResponseEntity<SignUpResponse> registerUser(@RequestBody SignupRequest signupRequest){
        return authService.registerUser(signupRequest);
    }

    @GetMapping("/account-info")
    public ResponseEntity<AccountDto> getAccountInfo(Authentication auth){
        return new ResponseEntity<>(userService.getAccountInfo(auth), HttpStatus.OK);
    }
}

