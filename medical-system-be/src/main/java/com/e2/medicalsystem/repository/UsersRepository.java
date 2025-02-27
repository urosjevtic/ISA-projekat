package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String email);
}
