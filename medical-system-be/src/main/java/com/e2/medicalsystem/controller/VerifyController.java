package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.model.User;
import com.e2.medicalsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/verify")
public class VerifyController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/{id}")
    public String verifyUser(@PathVariable Integer id){
        User user = usersService.getUserById(id);
        user.setEnabled(true);
        if(usersService.changeUserStatus(user) != null)
        {
            return "Sucessfull verification";
        }
        return "Verification error";
    }
}
