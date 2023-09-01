/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ou.cnh.filters.CustomAccessDeniedHandler;
import ou.cnh.filters.JwtAuthenticationTokenFilter;
import ou.cnh.filters.RestAuthenticationEntryPoint;

/**
 *
 * @author zedmo
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.dht.controllers",
    "com.dht.repository",
    "com.dht.service", 
    "com.dht.components",
    "ou.cnh.filters"
})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/routes/").permitAll();
        http.authorizeRequests().antMatchers("/api/stations/").permitAll();
        http.authorizeRequests().antMatchers("/api/routes/**").permitAll();
        http.authorizeRequests().antMatchers("/api/stations/**").permitAll();
        http.authorizeRequests().antMatchers("/api/trips/").permitAll();
        http.authorizeRequests().antMatchers("/api/trips/**").permitAll();
        http.authorizeRequests().antMatchers("/api/categories/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/comments/").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("hasRole('ROLE_Admin') or hasRole('ROLE_Client') or hasRole('ROLE_Driver') or hasRole('ROLE_Staff')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_Admin') or hasRole('ROLE_Client') or hasRole('ROLE_Driver') or hasRole('ROLE_Staff')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_Admin') or hasRole('ROLE_Client') or hasRole('ROLE_Driver') or hasRole('ROLE_Staff')").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}

