package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.UsersDto;
import com.e2.medicalsystem.model.Location;
import com.e2.medicalsystem.model.User;
import com.e2.medicalsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UsersController {

    private UsersService usersService;
    @Autowired
    public UsersController(UsersService usersService)
    {
        this.usersService = usersService;
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
    public ResponseEntity<UsersDto> saveUser(@RequestBody UsersDto userDto)
    {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setName(userDto.getName());
        newUser.setSurname(userDto.getSurname());
        newUser.setPhone(userDto.getPhone());
        newUser.setEmail(userDto.getEmail());
        newUser.setCity(userDto.getCity());
        newUser.setCountry(userDto.getCountry());
        newUser = usersService.saveUser(newUser);
        return new ResponseEntity<>(new UsersDto(newUser), HttpStatus.OK);
    }
}
