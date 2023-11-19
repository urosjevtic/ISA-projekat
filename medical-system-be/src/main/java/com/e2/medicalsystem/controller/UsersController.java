package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.PasswordChangeDto;
import com.e2.medicalsystem.dto.RegistrationInfoDto;
import com.e2.medicalsystem.dto.UserInfoDto;
import com.e2.medicalsystem.dto.UsersDto;
import com.e2.medicalsystem.model.User;
import com.e2.medicalsystem.security.AuthTokenFilter;
import com.e2.medicalsystem.service.EmailSenderService;
import com.e2.medicalsystem.service.UsersService;
import com.e2.medicalsystem.service.impl.EmailSenderServiceImpl;
import com.e2.medicalsystem.service.impl.UsersServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('ROLL_ADMIN')")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UsersDto>> getAllUsers(@AuthenticationPrincipal User u) {

        logger.info(u.getAuthorities().toString());

        List<User> allUsers = usersService.getAllUsers();
        List<UsersDto> allUsersDto = new ArrayList<>();
        for(User user : allUsers)
        {
            allUsersDto.add(new UsersDto(user));
        }
        return new ResponseEntity<>(allUsersDto, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsersDto> getById(@PathVariable Integer id){
        User user = usersService.getUserById(id);
        UsersDto userDto = new UsersDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    @GetMapping(value = "getUserInfo/{id}")
    @PreAuthorize("hasAuthority('ROLL_USER')")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable Integer id)
    {
        return new ResponseEntity<>(usersService.getUserInfo(id), HttpStatus.OK);
    }

    @PostMapping(value = "changePassword/{id}")
    @PreAuthorize("hasAuthority('ROLL_USER')")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeDto passwordChangeDto,@PathVariable Integer id)
    {
        usersService.changePassword(passwordChangeDto,id);
        return new ResponseEntity<String>("Password changed successfully!",HttpStatus.OK);
    }

    @PostMapping(value = "changeInfo/{id}")
    @PreAuthorize("hasAuthority('ROLL_USER')")
    public ResponseEntity<String> changeInfo(@RequestBody UserInfoDto userInfoDto,@PathVariable Integer id)
    {
        usersService.changeInfo(userInfoDto,id);
        return new ResponseEntity<String>("User info changed successfully!",HttpStatus.OK);
    }

    private User createNewUser(RegistrationInfoDto registrationInfoDto)
    {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(registrationInfoDto.getPassword()));
        newUser.setName(registrationInfoDto.getName());
        newUser.setSurname(registrationInfoDto.getSurname());
        newUser.setPhone(registrationInfoDto.getPhone());
        newUser.setEmail(registrationInfoDto.getEmail());
        newUser.setCity(registrationInfoDto.getCity());
        newUser.setCountry(registrationInfoDto.getCountry());
        newUser.setAddress(registrationInfoDto.getAddress());
        newUser.setProfession(registrationInfoDto.getProfession());
        newUser.setCompanyName(registrationInfoDto.getCompanyName());
        return newUser;
    }
}
