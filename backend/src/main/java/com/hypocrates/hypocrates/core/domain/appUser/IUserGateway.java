package com.hypocrates.hypocrates.core.domain.appUser;


public interface IUserGateway {
    boolean emailUsed(String email);
    boolean phoneUsed(String phone);

    User saveUser(User user);
}
