package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
}
