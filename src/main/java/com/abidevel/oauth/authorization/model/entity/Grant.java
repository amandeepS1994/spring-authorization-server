package com.abidevel.oauth.authorization.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.abidevel.oauth.authorization.model.enumeration.GrantType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "grant_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Grant {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(unique = true, nullable = false, length = 100)
   @Enumerated(EnumType.STRING)
   private GrantType name;
   @ToString.Exclude
   @EqualsAndHashCode.Exclude
   @Builder.Default
   @ManyToMany(fetch = FetchType.LAZY, targetEntity = Client.class, mappedBy = "grants")
   private Set<Client> clients = new HashSet<>();
}