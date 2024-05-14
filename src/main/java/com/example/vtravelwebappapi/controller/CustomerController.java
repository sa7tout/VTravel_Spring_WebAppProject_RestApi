package com.example.vtravelwebappapi.controller;

import com.example.vtravelwebappapi.dto.LoginRequest;
import com.example.vtravelwebappapi.model.Customer;
import com.example.vtravelwebappapi.service.CustomerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Initialize a single secret key for signing JWT tokens
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${jwt.expirationTime}")
    private long EXPIRATION_TIME;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        // Log before retrieving customers
        System.out.println("Fetching all customers...");
        List<Customer> customers = customerService.getAllCustomers();
        // Log the retrieved customers
        System.out.println("Retrieved customers: " + customers);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        // Log before retrieving customer by ID
        System.out.println("Fetching customer with ID: " + id);
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            // Log the retrieved customer
            System.out.println("Retrieved customer: " + customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        // Log before updating customer
        System.out.println("Updating customer with ID: " + id);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer != null) {
            // Log the updated customer
            System.out.println("Updated customer: " + updatedCustomer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        // Log before deleting customer
        System.out.println("Deleting customer with ID: " + id);
        customerService.deleteCustomer(id);
        // Log successful deletion
        System.out.println("Customer with ID " + id + " deleted successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails userDetails = customerService.loadUserByEmail(loginRequest.getEmail());
            if (userDetails != null && passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
                // Generate the session token
                String sessionToken = Jwts.builder()
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .signWith(key, SignatureAlgorithm.HS512)
                        .compact();

                Map<String, String> response = new HashMap<>();
                response.put("message", "Sign-in successful!");
                response.put("sessionToken", sessionToken);
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Invalid email or password.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            Map<String, String> response = new HashMap<>();
            response.put("error", "An unexpected error occurred during login.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /*@PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Customer customer) {
        try {

            // Validate customer data
            // Add validation logic here

            String encodedPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(encodedPassword);

            // Save customer to the database
            Customer createdCustomer = customerService.createCustomer(customer);
            if (createdCustomer != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully!");
            } else {
                System.out.println("Failed to create customer.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create account.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            System.out.println("An unexpected error occurred during sign-up: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred during sign-up.");
        }
    }*/

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Customer customer) {
        try {
            // Validate customer data
            // Add validation logic here

            String encodedPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(encodedPassword);

            // Save customer to the database
            Customer createdCustomer = customerService.createCustomer(customer);
            if (createdCustomer != null) {
                // Construct the response JSON object
                Map<String, String> response = new HashMap<>();
                response.put("message", "Account created successfully!");

                // Return the response entity
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                System.out.println("Failed to create customer.");
                // If creation failed, return an error response
                Map<String, String> response = new HashMap<>();
                response.put("error", "Failed to create account.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            System.out.println("An unexpected error occurred during sign-up: " + e.getMessage());
            // If an unexpected error occurred, return an error response
            Map<String, String> response = new HashMap<>();
            response.put("error", "An unexpected error occurred during sign-up.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Customer> getCustomerBySessionToken(@RequestHeader("Authorization") String sessionToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(sessionToken.replace("Bearer ", ""))
                    .getBody();
            String email = claims.getSubject();
            Customer customer = customerService.getCustomerByEmail(email);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/validate-session")
    public ResponseEntity<String> validateSession(@RequestHeader("Authorization") String sessionToken) {
        try {
            // Parse the claims from the session token using the initialized key
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(sessionToken.replace("Bearer ", ""))
                    .getBody();
            // Check if the session token has expired
            Date expirationDate = claims.getExpiration();
            if (expirationDate != null && expirationDate.before(new Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired");
            }

            // If all checks passed, return a success message
            return ResponseEntity.ok("Session valid");
        } catch (ExpiredJwtException e) {
            // Handle expired token exception
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid session token");
        }
    }



}
