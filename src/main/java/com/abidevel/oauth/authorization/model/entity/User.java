package com.abidevel.oauth.authorization.model.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.abidevel.oauth.authorization.model.enumeration.AuthorityTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    private String email;
    private String username;
    private LocalDateTime createdAt;
    private String password;
    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Authority.class)
    @JoinTable(
        name = "user_authority",
        joinColumns  = @JoinColumn(nullable = false, table = "user", name = "user_id" , referencedColumnName = "id" ),
        inverseJoinColumns = @JoinColumn(nullable = false, table = "authority",  name = "authority_id", referencedColumnName = "id")
    )
    private Set<Authority> authorities = new HashSet<>();
   
    public User addAuthority(Authority authority) {
        if (!this.authorities.contains(authority)) {
            this.authorities.add(authority);
            authority.getUsers().add(this);
        }
        return this;
    }
}
