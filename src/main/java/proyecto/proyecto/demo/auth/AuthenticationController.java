package proyecto.proyecto.demo.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controlador de autenticación
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService,
            @Qualifier("userDetailsService") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @SuppressWarnings("unused")
    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null || authenticationRequest.getCorreo() == null || authenticationRequest.getContrasenia() == null) {
            return ResponseEntity.badRequest().body("Error en los datos de entrada");
        }
    
        try {
            // Realizar la autenticación
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getCorreo(),
                            authenticationRequest.getContrasenia()));
    
            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getCorreo());
            String jwt = jwtService.generateToken(userDetails);
    
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en la autenticación: " + e.getMessage());
        }
    }
    
}

