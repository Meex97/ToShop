package com.project.Toshop.api;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GooglePublicKeysManager;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.project.Toshop.entity.Client;
import com.project.Toshop.entity.Supplier;
import com.project.Toshop.service.UserService;
import com.project.Toshop.entity.User;
import com.project.Toshop.security.JWT.JwtProvider;
import com.project.Toshop.vo.request.LoginForm;
import com.project.Toshop.vo.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Collections;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginForm loginForm) {
        // throws Exception if authentication failed
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generate(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findOne(userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.getName(), user.getRole(), user.getId()));
        } catch (AuthenticationException e) {
            try {
                //this.metodo1(loginForm); funzionaaaa
                //this.metodo2(loginForm);
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private void metodo1(LoginForm loginForm) { //------------------------FUNZIONAAAAAAAAAAAAAAAAAAAAAAAAA (forse)
        try {
            HttpTransport httpTransport = new NetHttpTransport();
            JsonFactory jsonFactory = new JacksonFactory();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                    // Specify the CLIENT_ID of the app that accesses the backend:
                    .setAudience(Collections.singletonList("250026481236-1tcpsas73dkp7nlb1kurknvsojrjiem7.apps.googleusercontent.com"))
                    // Or, if multiple clients access the backend:
                    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                    .build();
            // (Receive idTokenString by HTTPS POST)
            GoogleIdToken idToken = verifier.verify(loginForm.getGoogleIdToken());
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                // Print user identifier
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);
                // Get profile information from payload
                String email = payload.getEmail();
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");
                System.out.println("google retreived info:\n" + emailVerified + "\n");
                // Use or store profile information
                // ...

                User user = userService.findOne(email);
                if (user == null) {

                }

            } else {
                System.out.println("Invalid ID token.");
            }

        } catch (GeneralSecurityException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

/*
    private void metodo2(@RequestBody LoginForm loginForm) {
        if (request.getHeader("X-Requested-With") == null) {
            // Without the `X-Requested-With` header, this request could be forged. Aborts.
        }
    }
*/


    //TODO inserire codice di gestione accesso con google nel metodo sopra o in un nuovo metodo??

  /*  @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }*/

    @PostMapping("/registerClient")
    public ResponseEntity<?> save(@RequestBody Client client) {
        try {
            return ResponseEntity.ok(userService.save(client));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/registerSupplier")
    public ResponseEntity<?> save(@RequestBody Supplier supplier) {
        try {
            return ResponseEntity.ok(userService.save(supplier));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<User> update(@RequestBody User user, Principal principal) {

        try {
            if (!principal.getName().equals(user.getEmail())) throw new IllegalArgumentException();
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<User> getProfile(@PathVariable("email") String email, Principal principal) {
        if (principal.getName().equals(email)) {
            return ResponseEntity.ok(userService.findOne(email));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
