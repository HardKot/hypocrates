package com.hypocrates.hypocrates.interfaces;

import java.util.Date;

public interface IArrayValueStorage {
    boolean add(Object value);
    boolean delete(Object value);
    boolean has(Object value);
}
