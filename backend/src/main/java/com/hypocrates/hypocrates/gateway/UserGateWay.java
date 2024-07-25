package com.hypocrates.hypocrates.gateway;

import com.hypocrates.hypocrates.core.domain.appUser.IUserGateway;
import com.hypocrates.hypocrates.core.domain.appUser.User;
import org.springframework.stereotype.Component;

@Component
public class UserGateWay implements IUserGateway {

    @Override
    public boolean emailUsed(String email) {
        return false;
    }

    @Override
    public boolean phoneUsed(String phone) {
        return false;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }
}
