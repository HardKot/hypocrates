package com.hypocrates.hypocrates.domain;

public interface IUserGateway {
    boolean emailUsed(String email);
    boolean phoneUsed(String phone);
}
