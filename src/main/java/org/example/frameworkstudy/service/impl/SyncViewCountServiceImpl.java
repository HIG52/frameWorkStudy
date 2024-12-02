package org.example.frameworkstudy.service.impl;

import org.example.frameworkstudy.repository.BoardRepository;
import org.example.frameworkstudy.service.SyncViewCountService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SyncViewCountServiceImpl implements SyncViewCountService {

    private final RedisTemplate<String, Integer> redisTemplate;
    private final BoardRepository boardRepository;

    private static final String VIEW_COUNT_KEY_PREFIX = "board:views:";

    public SyncViewCountServiceImpl(RedisTemplate<String, Integer> redisTemplate, BoardRepository boardRepository) {
        this.redisTemplate = redisTemplate;
        this.boardRepository = boardRepository;
    }


    @Override
    @Scheduled(fixedRate = 60000) // 1분마다 실행
    public void syncViewCountsToDatabase() {
        Set<String> keys = redisTemplate.keys(VIEW_COUNT_KEY_PREFIX + "*");

        if (keys == null) return;

        for (String key : keys) {
            Integer boardId = Integer.parseInt(key.replace(VIEW_COUNT_KEY_PREFIX, ""));
            Integer viewCount = redisTemplate.opsForValue().get(key);

            if (viewCount != null && viewCount > 0) {
                // 데이터베이스에 조회수 업데이트
                boardRepository.incrementViewCount(boardId, viewCount);

                // Redis 데이터 초기화
                redisTemplate.delete(key);
            }
        }
    }
}
