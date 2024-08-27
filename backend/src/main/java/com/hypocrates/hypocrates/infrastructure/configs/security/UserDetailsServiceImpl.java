package com.hypocrates.hypocrates.infrastructure.configs.security;

import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private IStaffRepository staffRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
       var staffSchema = staffRepository.findByEmail(email);
       if (staffSchema.isEmpty()) {
           throw new UsernameNotFoundException(email);
       }

       return staffSchema.map(UserDetailsImpl::buildStaff).get();
    }
}
