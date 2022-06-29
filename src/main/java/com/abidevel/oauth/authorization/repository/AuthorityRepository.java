package com.abidevel.oauth.authorization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abidevel.oauth.authorization.model.entity.Authority;
import com.abidevel.oauth.authorization.model.enumeration.AuthorityTypes;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthorityName(AuthorityTypes type);
}
