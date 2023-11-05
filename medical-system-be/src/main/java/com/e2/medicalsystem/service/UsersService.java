package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.User;
import com.e2.medicalsystem.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    @Autowired
    public UsersService(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
    }


    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }

    public User saveUser(User user)
    {
        return usersRepository.save(user);
    }

    public User getUserById(Integer id)
    {
        return usersRepository.findById(id).orElse(null);
    }

}
