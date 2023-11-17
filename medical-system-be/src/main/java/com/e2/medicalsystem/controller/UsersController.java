package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.RegistrationInfoDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private EmailSenderService emailSenderService;
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('ROLL_USER')")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UsersDto>> getAllStudents(@AuthenticationPrincipal User u) {

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


    private User createNewUser(RegistrationInfoDto registrationInfoDto)
    {
        User newUser = new User();
        newUser.setPassword(registrationInfoDto.getPassword());
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
