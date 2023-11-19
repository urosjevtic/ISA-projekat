package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.dto.*;
import com.e2.medicalsystem.exception.InvalidPasswordException;
import com.e2.medicalsystem.model.ERole;
import com.e2.medicalsystem.model.User;
import com.e2.medicalsystem.repository.UsersRepository;
import com.e2.medicalsystem.security.AuthTokenFilter;
import com.e2.medicalsystem.security.JwtUtils;
import com.e2.medicalsystem.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }

    public User saveUser(RegistrationInfoDto registrationInfo)
    {
        User u = new User();
        u.setUsername(registrationInfo.getUsername());
        u.setEmail(registrationInfo.getEmail());
        u.setAddress(registrationInfo.getAddress());
        u.setCompanyName(registrationInfo.getCompanyName());
        u.setProfession(registrationInfo.getProfession());
        u.setCity(registrationInfo.getCity());
        u.setCountry(registrationInfo.getCountry());
        u.setPhone(registrationInfo.getPhone());
        u.setName(registrationInfo.getName());
        u.setSurname(registrationInfo.getSurname());
        u.setPassword(passwordEncoder.encode(registrationInfo.getPassword()));

        u.setRole(ERole.ROLL_USER);


        return this.usersRepository.save(u);
    }

    public User getUserById(Integer id)
    {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = usersRepository.findById(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        existingUser.setCompanyName(user.getCompanyName());
        existingUser.setProfession(user.getProfession());
        existingUser.setCity(user.getCity());
        existingUser.setCountry(user.getCountry());
        existingUser.setPhone(user.getPhone());
        existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());

        return usersRepository.save(existingUser);
    }

    @Override
    public User changeUserStatus(User user) {
        User existingUser = usersRepository.findById(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        existingUser.setEnabled(user.isEnabled());
        return usersRepository.save(existingUser);
    }

    public UserTokenState login(JwtAuthenticationRequest loginDto) {

        Optional<User> userOpt = usersRepository.findByUsername(loginDto.getUsername());
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "message: Incorrect credentials!");
        }


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User user = (User) authentication.getPrincipal();

        UserTokenState tokenDTO = new UserTokenState();
        tokenDTO.setAccessToken(jwt);
        tokenDTO.setExpiresIn(10000000L);

        return tokenDTO;
    }

    public UserInfoDto getUserInfo(int id)
    {
        User user = usersRepository.findById(id).orElseThrow();
        return new UserInfoDto(user.getEmail(),user.getUsername(),user.getName(),user.getSurname(),user.getCity(),user.getCountry(),user.getAddress(),user.getPhone(),user.getProfession(),user.getPenalPoints());
    }

    public void changePassword(PasswordChangeDto passwordChangeDto, int id)
    {
        User user = usersRepository.findById(id).orElseThrow();
        if(!passwordEncoder.matches(passwordChangeDto.oldPassword,user.getPassword())) throw new InvalidPasswordException("Password you entered does not match!");

        user.setPassword(passwordEncoder.encode(passwordChangeDto.newPassword));
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
        user.setAddress(userInfoDto.address);
    }



}
