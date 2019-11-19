package com.apps.shortener.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static com.apps.shortener.utils.Constants.COUNTER_INITIAL_VALUE;
import static com.apps.shortener.utils.Constants.REDIS_COUNTER_KEY;
import static com.apps.shortener.utils.Utils.convertBase10ToBase62;

@Component
public class RedisAtomicKeyGenerator implements KeyGenerator {

    /*
     * redis template will store [key-value] in the redis distributed cache system
     * redis is used as a distributed counter. so that at a time only one thread can change the value of the counter
     * redis INCR command is used to increment the counter value
     */

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public String generateKey() {

        //get latest counter value
        Long counterValue = getAtomicCounterValue();

        // convert the counter value to base62
        return convertBase10ToBase62(counterValue);
    }

    /**
     * increment the counter value by 1 and return
     */
    private Long getAtomicCounterValue() {
        if (isKeyExist()) {
            return getCounter();
        } else {
            return createAndGetCounter();
        }
    }

    /**
     * return the counter value after incrementing it
     */
    private Long getCounter() {
        return redisTemplate.opsForValue().increment(REDIS_COUNTER_KEY);
    }

    /**
     * if key does not exist in redis, this method will create one return the value
     */
    private Long createAndGetCounter() {
        synchronized (this) {
            if (!isKeyExist()) {
                redisTemplate.opsForValue().set(REDIS_COUNTER_KEY, COUNTER_INITIAL_VALUE.toString());
            }
            return redisTemplate.opsForValue().increment(REDIS_COUNTER_KEY);
        }
    }


    /**
     * check if the redis counter key exist in redis or not
     */
    private boolean isKeyExist() {
        return redisTemplate.hasKey(REDIS_COUNTER_KEY);
    }
}
