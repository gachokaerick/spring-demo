package com.example.demo.service;

import com.example.demo.data.LoginDTO;
import com.example.demo.repo.OtpRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.security.Otp;
import com.example.demo.security.User;
import com.example.demo.util.GenerateCodeUtil;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeUser() {
        Optional<User> userOptional = userRepository.findByUsername("admin");
        if (!userOptional.isPresent()) {
            User user = new User("admin", passwordEncoder.encode("letmein"),
                    "erick", "nairobi", "nairobi",
                    null, null, null);
            userRepository.save(user);
        }
    }

    public void auth(@Valid LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByUsername(loginDTO.getUserName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginDTO.getPassword(),
                    user.getPassword())) {
                renewOtp(user);
            } else {
                throw new BadCredentialsException("Incorrect auth credentials");
            }
        } else {
            throw new BadCredentialsException("User not found");
        }
    }

    public void renewOtp(User user) {
        String code = GenerateCodeUtil.generateCode();

        Optional<Otp> otpOptional = otpRepository.findByUsername(user.getUsername());
        Otp otp;
        if (otpOptional.isPresent()) {
            otp = otpOptional.get();
        } else {
            otp = new Otp();
            otp.setUsername(user.getUsername());
        }
        otp.setCode(code);
        otpRepository.save(otp);
    }

    public boolean validateOtp(@Valid Otp otpToValidate) {
        Optional<Otp> otpOptional = otpRepository.findByUsername(otpToValidate.getUsername());
        return otpOptional.map(otp -> otp.getCode().equals(otpToValidate.getCode())).orElse(false);
    }

    public User addUser(@Valid LoginDTO loginDTO) {
        User user = new User(loginDTO.getUserName(),
                passwordEncoder.encode(loginDTO.getPassword()),
                null, null, null,
                null, null, null);
        return userRepository.save(user);
    }
}
