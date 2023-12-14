package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.User;

public class UsersDto {
    private Integer id;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;

    public UsersDto(Integer id, String password, String name, String surname, String email, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public UsersDto(User user)
    {
        this(user.getId(), user.getPassword(), user.getName(), user.getSurname(),
                user.getEmail(), user.getPhone());

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
