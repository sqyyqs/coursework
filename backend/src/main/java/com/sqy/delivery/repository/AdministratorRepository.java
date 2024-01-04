package com.sqy.delivery.repository;

import com.sqy.delivery.domain.user.administratior.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Optional<Administrator> findByCredentials_Login(String login);
}
