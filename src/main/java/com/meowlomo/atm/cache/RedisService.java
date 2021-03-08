package com.meowlomo.atm.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisService<T> {

    @Value("${spring.cache.redis.time-to-live}")
    private long REDIS_TIME_TO_LIVE;

    public static AtomicBoolean REDIS_CONECTION_OK = new AtomicBoolean(true);
    private static final String LIST_PREFIX_STRING = "_LIST_";
    private static final String MAP_PREFIX_STRING = "_MAP_";

    private RedisTemplate<String, T> redisTemplate;
    private HashOperations<String, Object, T> hashOperation;
    private ListOperations<String, T> listOperation;
    private ValueOperations<String, T> valueOperations;

    @Autowired
    RedisService(RedisTemplate<String, T> redisTemplate) {
        if (REDIS_CONECTION_OK.get()) {
            this.redisTemplate = redisTemplate;
            this.hashOperation = redisTemplate.opsForHash();
            this.listOperation = redisTemplate.opsForList();
            this.valueOperations = redisTemplate.opsForValue();
        }
    }

    // map operations
    public void putMap(String redisKey, Object key, T data) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return; }
        hashOperation.put(MAP_PREFIX_STRING + redisKey, key, data);
        redisTemplate.expire(MAP_PREFIX_STRING + redisKey, REDIS_TIME_TO_LIVE, TimeUnit.MILLISECONDS);
    }

    public T getMapAsSingleEntry(String redisKey, Object key) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return null; }
        return hashOperation.get(MAP_PREFIX_STRING + redisKey, key);
    }

    public Map<Object, T> getMapAsAll(String redisKey) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return null; }
        return hashOperation.entries(MAP_PREFIX_STRING + redisKey);
    }

    // list operations
    public long putList(String redisKey, List<T> values) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return Long.valueOf(0); }
        Long returnValue = listOperation.rightPushAll(LIST_PREFIX_STRING + redisKey, values);
        redisTemplate.expire(LIST_PREFIX_STRING + redisKey, REDIS_TIME_TO_LIVE, TimeUnit.MILLISECONDS);
        return returnValue;
    }

    public List<T> getList(String redisKey) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return null; }
        return listOperation.range(LIST_PREFIX_STRING + redisKey, 0, listOperation.size(LIST_PREFIX_STRING + redisKey));
    }

    public Boolean deleteList(String redisKey) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return false; }
        return this.redisTemplate.delete(LIST_PREFIX_STRING + redisKey);
    }

    public Long deleteList(List<String> redisKeys) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return Long.valueOf(0); }
        List<String> newKeys = new ArrayList<String>();
        for (String oldKey : redisKeys) {
            newKeys.add(LIST_PREFIX_STRING + oldKey);
        }
        return this.redisTemplate.delete(newKeys);
    }

    public Long deleteListPattern(String parttern) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return Long.valueOf(0); }
        Set<String> keys = redisTemplate.keys(LIST_PREFIX_STRING + parttern);
        return this.redisTemplate.delete(keys);
    }

    public Boolean hasList(String key) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return false; }
        return redisTemplate.hasKey(LIST_PREFIX_STRING + key);
    }

    // value operations
    public void putValue(String key, T value) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return; }
        valueOperations.set(key, value, REDIS_TIME_TO_LIVE, TimeUnit.MILLISECONDS);
    }

    public void putValueWithExpireTime(String key, T value, long timeout, TimeUnit unit) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return; }
        valueOperations.set(key, value, timeout, unit);
    }

    public T getValue(String key) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return null; }
        return valueOperations.get(key);
    }

    public void setExpire(String key, long timeout, TimeUnit unit) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return; }
        redisTemplate.expire(key, timeout, unit);
    }

    // common operations
    public Boolean delete(String redisKey) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return false; }
        return this.redisTemplate.delete(redisKey);
    }

    public Long delete(List<String> redisKeys) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return Long.valueOf(0); }
        return this.redisTemplate.delete(redisKeys);
    }

    public Long deleteByKeyPattern(String parttern) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return Long.valueOf(0); }
        Set<String> keys = redisTemplate.keys(parttern);
        return this.redisTemplate.delete(keys);
    }

    public Long deleteAllMatch(String parttern) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return Long.valueOf(0); }
        Set<String> keys = redisTemplate.keys("*" + parttern + "*");
        return this.redisTemplate.delete(keys);
    }

    public Boolean hasKey(String key) {
        if (!RedisService.REDIS_CONECTION_OK.get()) { return false; }
        return redisTemplate.hasKey(key);
    }
}