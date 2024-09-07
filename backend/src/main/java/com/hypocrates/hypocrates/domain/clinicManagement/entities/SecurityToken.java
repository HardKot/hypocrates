package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class SecurityToken extends AbstractClinicEntity {
  private String token;

  @Column(name ="is_banned")
  private boolean isBanned;

  private String ip;

  @ManyToOne
  private Staff staff;
}