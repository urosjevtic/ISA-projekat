package com.e2.medicalsystem.service;

import com.e2.medicalsystem.dto.RegistrationInfoDto;
import com.e2.medicalsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    public List<User> getAllUsers();
    public User saveUser(RegistrationInfoDto registrationInfo);
    public User getUserById(Integer id);
    public Optional<User> findByUsername(String username);
}
