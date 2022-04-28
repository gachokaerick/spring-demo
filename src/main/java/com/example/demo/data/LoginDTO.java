package com.example.demo.data;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {
    @NotNull
    @Size(min = 3, message = "Username must have at least 3 characters")
    private String userName;
    @NotNull
    @Size(min = 8, message = "Password must be 8 characters long")
    private String password;
}
