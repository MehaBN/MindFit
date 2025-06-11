package com.prog.security;

import com.prog.entity.User;  // Your custom User entity
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private User user;  // Store the custom User entity

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        // Assuming your User entity has a list of roles (you could replace this with actual roles)
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // You can add roles if your User entity has them
        // Example: authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }
    
    
    //-----------------
    
    public Collection<SimpleGrantedAuthority> getAuthorities1() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

    
    //==============================
    
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/**").permitAll()
            .and()
            .formLogin()
            .and()
            .logout();
    }

    
    
    //=================================

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize based on your logic (e.g., based on an expiration date)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize based on your logic (e.g., check account lock status)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize based on your logic (e.g., check last password change date)
    }

    @Override
    public boolean isEnabled() {
        return true; // Customize based on your logic (e.g., check if the user is active)
    }

    // Additional getter for age and bio if needed
    public int getAge() {
        return user.getAge();
    }

    public String getBio() {
        return user.getBio();
    }
    
    public String getGender() {
        return user.getGender();
    }
    
    public double getWeight() {
        return user.getWeight();
    }
    
    public double getHeight() {
        return user.getHeight();
    }
}
