package com.hypocrates.hypocrates.entity.user;


public interface IUserGateway {
    boolean emailUsed(String email);
    boolean phoneUsed(String phone);

    UserModel saveUser(UserModel user);
}
