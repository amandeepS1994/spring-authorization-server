package com.abidevel.oauth.authorization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abidevel.oauth.authorization.model.entity.Grant;
import com.abidevel.oauth.authorization.model.enumeration.GrantType;

@Repository
public interface GrantRepository extends JpaRepository<Grant, Long> {
    Optional<Grant> findByName (GrantType gType); 
    Optional<Grant> findByName (String gType); 
}
 