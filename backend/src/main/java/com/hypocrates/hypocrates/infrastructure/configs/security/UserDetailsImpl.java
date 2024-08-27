package com.hypocrates.hypocrates.infrastructure.configs.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hypocrates.hypocrates.configs.database.clinics.schema.StaffSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private UUID id;
    private String email;
    private boolean emailIsActive;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl buildStaff(StaffSchema staff) {

        return new UserDetailsImpl(
                staff.getId(),
                staff.getEmail(),
                staff.getEmailIsActive(),
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
        return emailIsActive;
    }
}
