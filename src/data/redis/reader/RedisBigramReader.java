package data.redis.reader;

import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;


public class RedisBigramReader extends RedisReader {
    private double occurrenceCount;

    public RedisBigramReader() {
        Jedis jedis = RedisConfig.jedis;
        occurrenceCount = Double.parseDouble(jedis.get(RedisConfig.BIGRAMS_OCCURRENCE_COUNT));
    }

    String getHashMapWordsName() {
        return RedisConfig.BIGRAMS;
    }

    double getOverallOccurrenceCount() {
        return occurrenceCount;
    }

    public double read(String firstWord, String secondWord) {
        return read(firstWord + "_" + secondWord);
    }
}
