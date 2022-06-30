package com.abidevel.oauth.authorization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abidevel.oauth.authorization.model.entity.Scope;
import com.abidevel.oauth.authorization.model.enumeration.ScopeType;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, Long> {
    Optional<Scope> findByName (ScopeType scopeType);
    Optional<Scope> findByName (String scopeType);
}
