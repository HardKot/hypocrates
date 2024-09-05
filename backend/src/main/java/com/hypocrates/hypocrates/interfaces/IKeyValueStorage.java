package com.hypocrates.hypocrates.interfaces;

import java.util.Date;

public interface IKeyValueStorage {
    boolean write(String key, Object value, Date date);
    boolean write(String key, Object value);

    boolean delete(String key);

    Object read(String key);

    IArrayValueStorage arrayValueStorage(String key);
}
