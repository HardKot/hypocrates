package com.hypocrates.hypocrates.infrastructure.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.PatientSchema;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.StaffSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private UUID id;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl buildStaff(StaffSchema staff) {

        return new UserDetailsImpl(
                staff.getId(),
                staff.getEmail(),
                staff.getPassword(),
                staff.getRules()
                        .stream()
                        .map(appRule -> new SimpleGrantedAuthority(appRule.name()))
                        .toList()
        );
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
