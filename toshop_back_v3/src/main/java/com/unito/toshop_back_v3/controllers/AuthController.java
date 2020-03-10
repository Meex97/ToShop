package com.unito.toshop_back_v3.controllers;


import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.unito.toshop_back_v3.models.*;

import com.unito.toshop_back_v3.payload.request.LoginRequest;
import com.unito.toshop_back_v3.payload.request.SignupRequest;
import com.unito.toshop_back_v3.payload.request.SignupRequestClient;
import com.unito.toshop_back_v3.payload.request.SignupRequestSupplier;
import com.unito.toshop_back_v3.payload.response.JwtResponse;
import com.unito.toshop_back_v3.payload.response.MessageResponse;
import com.unito.toshop_back_v3.repository.RoleRepository;
import com.unito.toshop_back_v3.repository.UserRepository;
import com.unito.toshop_back_v3.security.jwt.JwtUtils;
import com.unito.toshop_back_v3.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    /*
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_SUPPLIER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
   */


    @PostMapping("/signupClient")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestClient signupRequestClient) {
        if (userRepository.existsByUsername(signupRequestClient.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequestClient.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Client client = new Client(signupRequestClient.getUsername(), signupRequestClient.getEmail(), encoder.encode(signupRequestClient.getPassword()), signupRequestClient.getName(),signupRequestClient.getSurname(),signupRequestClient.getPhone(),signupRequestClient.getAddress());

        Set<String> strRoles = signupRequestClient.getRole();
        Set<Role> roles = new HashSet<>();


        Role clientRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(clientRole);

        client.setRoles(roles);
        userRepository.save(client);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



    @PostMapping("/signupSupplier")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestSupplier signupRequestSupplier) {
        if (userRepository.existsByUsername(signupRequestSupplier.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequestSupplier.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Supplier supplier = new Supplier(signupRequestSupplier.getUsername(), signupRequestSupplier.getEmail(), encoder.encode(signupRequestSupplier.getPassword()), signupRequestSupplier.getName(),signupRequestSupplier.getSurname(),signupRequestSupplier.getPhone(), signupRequestSupplier.getpIVA(),signupRequestSupplier.getAddress(), signupRequestSupplier.getShopName());

        Set<String> strRoles = signupRequestSupplier.getRole();
        Set<Role> roles = new HashSet<>();

        Role supplierRole = roleRepository.findByName(ERole.ROLE_SUPPLIER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(supplierRole);

        supplier.setRoles(roles);
        userRepository.save(supplier);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



}
