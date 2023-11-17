package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.dto.JwtAuthenticationRequest;
import com.e2.medicalsystem.dto.RegistrationInfoDto;
import com.e2.medicalsystem.dto.UserTokenState;
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

    public UserTokenState login(JwtAuthenticationRequest loginDto) {

        Optional<User> userOpt = usersRepository.findByUsername(loginDto.getUsername());
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "message: Incorrect credentials!");
        }

        String encodedPassword = passwordEncoder.encode(loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), encodedPassword));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User user = (User) authentication.getPrincipal();


        UserTokenState tokenDTO = new UserTokenState();
        tokenDTO.setAccessToken(jwt);
        //tokenDTO.setExpiresIn();
        //tokenDTO.setUserId(user.getId());
        //tokenDTO.setUserRole(user.getRole());
        //tokenDTO.setEmail(user.getEmail());

        return tokenDTO;
    }

}
