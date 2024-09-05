package com.hypocrates.hypocrates.infrastructure.facade;

import com.hypocrates.hypocrates.interfaces.IArrayValueStorage;
import com.hypocrates.hypocrates.interfaces.IKeyValueStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KeyValueStorageFacade implements IKeyValueStorage {
    private final RedisTemplate<String, Object> redisTemplate;

    public boolean write(String key, Object value) {
        LocalDateTime expire = LocalDateTime.now().plusMinutes(20);

        return write(key, value, Date.from(expire.toInstant(ZoneOffset.UTC)));
    }

    public boolean write(String key, Object value, Date date) {
        if (value == null) {
            return false;
        }

        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expireAt(key, date);

        return true;
    }

    @Override
    public boolean delete(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
        return false;
    }

    @Override
    public Object read(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public IArrayValueStorage arrayValueStorage(String key) {
        return new ArrayValueStorage(key, redisTemplate);
    }

    @RequiredArgsConstructor
    public static class ArrayValueStorage implements IArrayValueStorage {
        private final String key;
        private final RedisTemplate<String, Object> redisTemplate;

        public boolean add(Object value) {
            try {
                if (value == null) return false;
                redisTemplate.opsForList().rightPush(key, value);
                return true;
            } catch (NullPointerException exception) {
                return false;
            }
        }


        public boolean delete(Object value) {
            try {
                if (value == null) return false;
                redisTemplate.opsForList().remove(key, 1, value);
                return true;
            } catch (NullPointerException exception) {
                return false;
            }
        }

        public boolean has(Object value) {
            try {
                List<Object> list = redisTemplate.opsForList().range(key, 0, -1);
                if (list == null) return false;

                for (Object o : list) {
                    if (o.equals(value)) return true;
                }

                return false;
            } catch (NullPointerException exception) {
                return false;
            }
        }
    }
}
