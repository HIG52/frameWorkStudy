package org.example.frameworkstudy.service.impl;

import org.example.frameworkstudy.service.ViewCountService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViewCountServiceImpl implements ViewCountService {

    private final RedisTemplate<String, Integer> redisTemplate;

    private static final String VIEW_COUNT_KEY_PREFIX = "board:views:";

    public ViewCountServiceImpl(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void incrementViewCount(int boardId) {
        String key = VIEW_COUNT_KEY_PREFIX + boardId;
        redisTemplate.opsForValue().increment(key);
    }

    @Override
    public int getViewCount(int boardId) {
        String key = VIEW_COUNT_KEY_PREFIX + boardId;
        Integer count = redisTemplate.opsForValue().get(key);
        return count != null ? count : 0;
    }
}
