package com.hypocrates.hypocrates.interfaces;

public interface IRandomFacade {
    String randomCode();

    String randomString(int length);

    String randomLowerChars(int length);
}
