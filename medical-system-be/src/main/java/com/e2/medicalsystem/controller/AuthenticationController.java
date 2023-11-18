package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.JwtAuthenticationRequest;
import com.e2.medicalsystem.dto.RegistrationInfoDto;
import com.e2.medicalsystem.dto.UserTokenState;
import com.e2.medicalsystem.exception.UsernameAlreadyExistException;
import com.e2.medicalsystem.model.User;
import com.e2.medicalsystem.service.EmailSenderService;
import com.e2.medicalsystem.service.impl.UsersServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.e2.medicalsystem.dto.UserTokenState;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {


    @Autowired
    private UsersServiceImpl userService;

    @Autowired
    private EmailSenderService emailService;

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody RegistrationInfoDto registratonInfo, UriComponentsBuilder ucBuilder) {
        Optional<User> existUser = this.userService.findByUsername(registratonInfo.getUsername());

        if (existUser.isPresent()) {
            throw new UsernameAlreadyExistException("Username already exists");
        }

        User user = this.userService.saveUser(registratonInfo);

        String verificationLink = "http://localhost:8080/api/verify/" + user.getId();
        String verificationMail = generateVerificationEmail(user.getUsername(), verificationLink);

        emailService.sendEmail(user.getEmail(), "Verification email", verificationMail);
        System.out.println("Email poslat valjda...");

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    private String generateVerificationEmail(String userName, String verificationLink) {
        return String.format("Subject: Verify Your Profile\n\n" +
                "Dear %s,\n\n" +
                "Thank you for signing up with our medical equipment system!\n\n" +
                "To complete the registration process, please click the following link to verify your email address:\n\n" +
                "%s\n\n" +
                "Best regards,\n" +
                "Marko, Uros and Filip", userName, verificationLink);
    }

    @PostMapping(value = "login", consumes = "application/json")
    public ResponseEntity<UserTokenState> login(@RequestBody JwtAuthenticationRequest loginDto, HttpServletRequest request){
        return ResponseEntity.ok(userService.login(loginDto));
    }
}
