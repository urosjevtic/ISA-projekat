package com.e2.medicalsystem.service;
import com.e2.medicalsystem.dto.PasswordChangeDto;
import com.e2.medicalsystem.dto.RegistrationInfoDto;
import com.e2.medicalsystem.dto.UserInfoDto;
import com.e2.medicalsystem.model.User;
import java.util.List;
import java.util.Optional;
public interface UsersService {
    public List<User> getAllUsers();
    public User saveUser(RegistrationInfoDto registrationInfo);
    public User getUserById(Integer id);
    public Optional<User> findByUsername(String username);
    public User updateUser(User user);
    public User changeUserStatus(User user);
    public UserInfoDto getUserInfo(int id);
    public void changePassword(PasswordChangeDto passwordChangeDto, int id);
    public void changeInfo(UserInfoDto userInfoDto,int id);
}
