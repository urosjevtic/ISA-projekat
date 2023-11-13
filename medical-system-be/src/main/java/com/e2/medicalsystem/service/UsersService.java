package com.e2.medicalsystem.service;

import com.e2.medicalsystem.dto.PasswordChangeDto;
import com.e2.medicalsystem.dto.UserInfoDto;
import com.e2.medicalsystem.exception.EmailAlreadyExistException;
import com.e2.medicalsystem.exception.InvalidPasswordException;
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
        if(usersRepository.findByEmail(user.getEmail()) != null)
        {
            throw new EmailAlreadyExistException("Email already exist");
        }
        return usersRepository.save(user);
    }

    public User getUserById(Integer id)
    {
        return usersRepository.findById(id).orElse(null);
    }

    public UserInfoDto getUserInfo(int id)
    {
        User user = usersRepository.findById(id).orElseThrow();
        return new UserInfoDto(user.getEmail(),user.getName(),user.getSurname(),user.getCity(),user.getCountry(),user.getPhone(),user.getProfession());
    }

    public void changePassword(PasswordChangeDto passwordChangeDto,int id)
    {
        User user = usersRepository.findById(id).orElseThrow();
        if(!user.getPassword().equals(passwordChangeDto.oldPassword)) throw new InvalidPasswordException("Password you entered does not match!");

        user.setPassword(passwordChangeDto.newPassword);
        usersRepository.save(user);

    }

    public void changeInfo(UserInfoDto userInfoDto,int id)
    {
        User user = usersRepository.findById(id).orElseThrow();
        setUserInfo(user,userInfoDto);
        usersRepository.save(user);
    }

    private void setUserInfo(User user,UserInfoDto userInfoDto)
    {
        user.setName(userInfoDto.name);
        user.setSurname(userInfoDto.surname);
        user.setCountry(userInfoDto.country);
        user.setCity(userInfoDto.city);
        user.setPhone(userInfoDto.phone);
        user.setProfession(userInfoDto.profession);
    }

}
