package com.example.mesadigitalwebservices.repository;

import com.example.mesadigitalwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
