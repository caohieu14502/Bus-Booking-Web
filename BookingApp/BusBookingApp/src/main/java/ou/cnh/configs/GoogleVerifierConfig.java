/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.configs;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author zedmo
 */
@Configuration
public class GoogleVerifierConfig extends WebSecurityConfigurerAdapter{
    
    @Bean
    public GoogleIdTokenVerifier verifier(){
        return new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
        // Specify the CLIENT_ID of the app that accesses the backend:
        .setAudience(Collections.singletonList("851897019747-ha2l1kk2jjo2rlom8pe6t7tgrudfgoao.apps.googleusercontent.com"))
        // Or, if multiple clients access the backend:
        //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
        .build();
    }

}
