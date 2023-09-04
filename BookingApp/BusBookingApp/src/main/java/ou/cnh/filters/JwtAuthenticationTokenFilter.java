/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.filters;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import ou.cnh.components.JwtService;
import ou.cnh.pojo.User;
import ou.cnh.service.UserService;

/**
 *
 * @author huu-thanhduong
 */
public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final static String TOKEN_HEADER = "authorization";
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoogleIdTokenVerifier verifier;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(TOKEN_HEADER);
        System.out.print(authToken);

        boolean tokenIsValid = false;
        GoogleIdToken idToken = null;
        if (authToken != null) {
            idToken = GoogleIdToken.parse(verifier.getJsonFactory(), authToken);
            try {
                tokenIsValid = (idToken != null) && verifier.verify(idToken);
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(JwtAuthenticationTokenFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        boolean jwtNormalIsValid = jwtService.validateTokenLogin(authToken);
        if (jwtNormalIsValid || tokenIsValid) {
            String username = "nulllllllllllllllllllllllll";
            if (jwtNormalIsValid) {
                username = jwtService.getUsernameFromToken(authToken);
            } else if (tokenIsValid) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                username = payload.getEmail();

                if (userService.getUserByMail(username) == null) {
                    User nu = new User();

                    // Print user identifier
                    String userId = payload.getSubject();
                    System.out.println("User ID: " + userId);

                    // Get profile information from payload
                    nu.setEmail(payload.getEmail());
                    nu.setAvatar((String) payload.get("picture"));
                    nu.setLastName((String) payload.get("family_name"));
                    nu.setFirstName((String) payload.get("given_name"));
                    nu.setPassword("123456");

                    userService.addOrUpdateUser(nu);
                }

            }
            System.out.printf("^^^^^^^^^^^^\n%s\n^^^^^^^^^^^^^", username);
            User user = userService.getUserByMail(username);
            if (user != null) {
                boolean enabled = true;
                boolean accountNonExpired = true;
                boolean credentialsNonExpired = true;
                boolean accountNonLocked = true;

                Set<GrantedAuthority> authorities = new HashSet<>();
                authorities.add(new SimpleGrantedAuthority(user.getRoleId().getRoleName()));

                UserDetails userDetail = new org.springframework.security.core.userdetails.User(username, user.getPassword(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked, authorities);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
                        null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
