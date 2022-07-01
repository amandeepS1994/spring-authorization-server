package com.abidevel.oauth.authorization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abidevel.oauth.authorization.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByUsername(String username);
    Optional<Client>findByUsername(String username);
    Optional<Client>findById(int clientId);
    Optional<Client>findById(Long clientId);
    Optional<Client>findById(String clientId);

}
