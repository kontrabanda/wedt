package data.redis.reader;

import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;

public class RedisWordReader extends RedisReader{
    private double occurrenceCount;

    public RedisWordReader() {
        Jedis jedis = RedisConfig.jedis;
        occurrenceCount = Double.parseDouble(jedis.get(RedisConfig.WORDS_OCCURRENCE_COUNT));
    }

    String getHashMapWordsName() {
        return RedisConfig.WORDS;
    }

    double getOverallOccurrenceCount() {
        return occurrenceCount;
    }
}
