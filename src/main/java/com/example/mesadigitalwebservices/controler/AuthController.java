package com.example.mesadigitalwebservices.controler;

import com.example.mesadigitalwebservices.dto.AuthenticationRequest;
import com.example.mesadigitalwebservices.dto.AuthenticationResponse;
import com.example.mesadigitalwebservices.dto.UserRegistrationRequest;
import com.example.mesadigitalwebservices.entity.user.User;
import com.example.mesadigitalwebservices.repository.user.UserRepository;
import com.example.mesadigitalwebservices.service.JwtService;
import com.example.mesadigitalwebservices.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        userService.registerUser(request);
        return new ResponseEntity<>("Usuário registrado com sucesso", HttpStatus.CREATED);
    }

    @GetMapping("/list/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}

@Getter
@Setter
class AuthRequest {
    private String username;
    private String password;

}

@Getter
class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
