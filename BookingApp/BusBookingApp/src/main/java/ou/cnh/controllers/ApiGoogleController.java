/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ou.cnh.components.JwtService;
import ou.cnh.pojo.User;
import ou.cnh.service.UserService;

/**
 *
 * @author zedmo
 */
@RestController
@RequestMapping("/api")
public class ApiGoogleController {

    @Autowired
    private GoogleIdTokenVerifier verifier;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login/google/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestParam(value = "tokenId") String idTokenString) throws GeneralSecurityException, IOException {

        GoogleIdToken idToken = GoogleIdToken.parse(verifier.getJsonFactory(), idTokenString);
        boolean tokenIsValid = (idToken != null) && verifier.verify(idToken);

        if (tokenIsValid) {
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

            // Use or store profile information
            // ...
            if (this.userService.getUserByMail(email) == null) {
                // Dang ki
            }
            if (emailVerified) {
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

}
