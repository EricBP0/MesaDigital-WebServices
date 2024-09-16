package com.example.mesadigitalwebservices.repository;

import com.example.mesadigitalwebservices.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
}
