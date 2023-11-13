package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.RegistrationInfoDto;
import com.e2.medicalsystem.dto.UsersDto;
import com.e2.medicalsystem.model.User;
import com.e2.medicalsystem.service.EmailSenderService;
import com.e2.medicalsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UsersController {

    private UsersService usersService;
    private EmailSenderService emailSenderService;
    @Autowired
    public UsersController(UsersService usersService, EmailSenderService emailSenderService)
    {
        this.usersService = usersService;
        this.emailSenderService = emailSenderService;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<UsersDto>> getAllStudents() {

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

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UsersDto> saveUser(@RequestBody RegistrationInfoDto registrationInfoDto)
    {
        User newUser = new User();
        newUser = usersService.saveUser(createNewUser(registrationInfoDto));
        emailSenderService.sendEmail(newUser.getEmail(), "Confirmation mail", "Confirm your registration: http://localhost:4200");
        return new ResponseEntity<>(new UsersDto(newUser), HttpStatus.OK);
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
