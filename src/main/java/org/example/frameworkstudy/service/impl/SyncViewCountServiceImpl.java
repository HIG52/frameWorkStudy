package org.example.frameworkstudy.service.impl;

import jakarta.transaction.Transactional;
import org.example.frameworkstudy.repository.BoardRepository;
import org.example.frameworkstudy.service.SyncViewCountService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SyncViewCountServiceImpl implements SyncViewCountService {

    private final RedisTemplate<String, String> redisTemplate;
    private final BoardRepository boardRepository;

    private static final String VIEW_COUNT_KEY_PREFIX = "board:views:";

    public SyncViewCountServiceImpl(RedisTemplate<String, String> redisTemplate, BoardRepository boardRepository) {
        this.redisTemplate = redisTemplate;
        this.boardRepository = boardRepository;
    }


    @Override
    @Scheduled(fixedRate = 60000) // 1분마다 실행
    @Transactional
    public void syncViewCountsToDatabase() {
        Set<String> keys = redisTemplate.keys(VIEW_COUNT_KEY_PREFIX + "*");

        if (keys == null) return;

        for (String key : keys) {
            Integer boardId = Integer.parseInt(key.replace(VIEW_COUNT_KEY_PREFIX, ""));
            // Redis에서 조회수(String)를 가져와 Integer로 변환
            String viewCountStr = redisTemplate.opsForValue().get(key);
            Integer viewCount = viewCountStr != null ? Integer.parseInt(viewCountStr) : 0;

            System.out.println("viewCount = " + viewCount);
            if (viewCount != null && viewCount > 0) {
                // 데이터베이스에 조회수 업데이트
                boardRepository.incrementViewCount(boardId, viewCount);

                // Redis 데이터 초기화
                redisTemplate.delete(key);
            }
        }
    }
}
