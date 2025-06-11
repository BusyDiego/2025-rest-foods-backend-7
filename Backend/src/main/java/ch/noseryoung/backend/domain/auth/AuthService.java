package ch.noseryoung.backend.domain.auth;

import ch.noseryoung.backend.domain.auth.dto.AuthResponse;
import ch.noseryoung.backend.domain.auth.dto.LoginRequest;
import ch.noseryoung.backend.domain.auth.dto.SignupRequest;
import ch.noseryoung.backend.domain.user.User;
import ch.noseryoung.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    public AuthResponse signup(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return new AuthResponse(null, null, "Username is already taken!");
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, null, "Email is already in use!");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of("ROLE_USER"));
        user.setEnabled(true);
        
        userRepository.save(user);
        
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token, user.getUsername(), "User registered successfully!");
    }
    
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);
        
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse(null, null, "Invalid username or password!");
        }
        
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token, user.getUsername(), "Login successful!");
    }
    
    public AuthResponse validateToken(String token) {
        try {
            String username = jwtUtil.extractUsername(token);
            if (jwtUtil.validateToken(token, username)) {
                return new AuthResponse(token, username, "Token is valid");
            }
        } catch (Exception e) {
            // Invalid token
        }
        return new AuthResponse(null, null, "Invalid or expired token");
    }
}