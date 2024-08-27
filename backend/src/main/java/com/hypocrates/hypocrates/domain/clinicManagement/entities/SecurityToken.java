package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class SecurityToken extends AbstractClinicEntity {
  private String token;

  private boolean isBanned;

  private String ip;

  @ManyToOne
  private Staff staff;
}