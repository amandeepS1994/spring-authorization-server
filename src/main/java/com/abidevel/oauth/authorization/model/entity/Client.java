package com.abidevel.oauth.authorization.model.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.abidevel.oauth.authorization.model.enumeration.GrantType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    private String secret;
    private LocalDateTime createdAt;
    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Grant.class)
    @JoinTable(name = "client_grant_type", 
    joinColumns = @JoinColumn(table = "client", nullable = false, name = "client_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(table = "grant_type", nullable = false, name = "grant_id", referencedColumnName = "id"))
    private Set<Grant> grants = new HashSet<>();
    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Scope.class)
    @JoinTable(name = "client_scope",
    joinColumns = @JoinColumn(table = "client", nullable = false, name = "client_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(table = "scope",nullable = false, name = "scope_id", referencedColumnName = "id"))
    private Set<Scope> scopes = new HashSet<>();

    public Client addGrant (Grant grant) {
        if (!this.grants.contains(grant)) {
            this.grants.add(grant);
            grant.getClients().add(this);
        }
        return this;
    }

    public Client addScope (Scope scope) {
        if (!this.scopes.contains(scope)) {
            this.scopes.add(scope);
            scope.getClients().add(this);
        }
        return this;
    }
}

