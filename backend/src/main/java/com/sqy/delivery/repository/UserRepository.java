package com.sqy.delivery.repository;

import com.sqy.delivery.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    boolean existsByCredentials_Login(String login);

    Optional<User> findByCredentials_Login(String login);

//    User findByCredentials_Login(String login);


}
