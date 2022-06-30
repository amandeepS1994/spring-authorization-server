package com.abidevel.oauth.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abidevel.oauth.authorization.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByUsername(String username);
}
