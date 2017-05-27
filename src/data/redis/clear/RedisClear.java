package data.redis.clear;

import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;

public class RedisClear {
    private Jedis jedis = RedisConfig.jedis;

    public void clear() {
        jedis.set(RedisConfig.WORDS_OCCURRENCE_COUNT, "0");
        jedis.del(RedisConfig.WORDS);
        jedis.set(RedisConfig.BIGRAMS_OCCURRENCE_COUNT, "0");
        jedis.del(RedisConfig.BIGRAMS);
    }
}
