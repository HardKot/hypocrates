package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.models.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private StaffService staffService;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
       var staffSchema = staffService.findByEmail(email);
       if (staffSchema == null) {
           throw new UsernameNotFoundException(email);
       }

       return UserDetailsImpl.buildStaff(staffSchema);
    }
}
