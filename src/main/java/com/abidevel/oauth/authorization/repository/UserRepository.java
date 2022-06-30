package com.abidevel.oauth.authorization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abidevel.oauth.authorization.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email); 
    Optional<User> findByEmail (String email);
    Optional<User> findByUsername (String email);

}
