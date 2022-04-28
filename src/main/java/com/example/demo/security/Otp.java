package com.example.demo.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Otp {

    @Id
    @NotNull(message = "Username is required")
    private String username;

    @Column(nullable = false, unique = true)
    @NotNull(message = "code is required")
    private String code;
}
