package com.example.demo.web;

import com.example.demo.data.LoginDTO;
import com.example.demo.security.Otp;
import com.example.demo.security.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.addUser(loginDTO));
    }

    @PostMapping("/user/auth")
    public ResponseEntity<Void> auth(@RequestBody LoginDTO loginDTO) {
        userService.auth(loginDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/otp/check")
    public ResponseEntity<Void> checkOtp(@RequestBody Otp otp) {
        if (userService.validateOtp(otp)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
