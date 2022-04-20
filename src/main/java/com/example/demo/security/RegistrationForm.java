package com.example.demo.security;

import com.example.demo.security.enumeration.Roles;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                Collections.singletonList(new SimpleGrantedAuthority(Roles.ROLE_USER.toString())),
                fullname, street, city, state, zip, phone);
    }

}
