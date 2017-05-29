package data.redis.reader;

import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;

public class RedisBigramReader extends RedisReader {
    private Jedis jedis = RedisConfig.jedis;
    private double occurrenceCount;

    public RedisBigramReader() {
        Jedis jedis = RedisConfig.jedis;
        occurrenceCount = Double.parseDouble(jedis.get(RedisConfig.BIGRAMS_OCCURRENCE_COUNT));
    }

    @Override
    String getHashMapName() {
        return RedisConfig.BIGRAMS;
    }

    @Override
    double getOverallOccurrenceCount() {
        return occurrenceCount;
    }

    public double read(String firstWord, String secondWord) {
        occurrenceCount = getWordOccurrenceCount(firstWord);
        return read(firstWord + RedisConfig.BIGRAM_SEPARATOR + secondWord);
    }

    private double getWordOccurrenceCount(String word) {
        try {
            return Double.parseDouble(jedis.hget(RedisConfig.WORDS, word));
        } catch (NullPointerException e) {
            System.out.println("getWordOccurrenceCount: problem with parsing value=" + word);
            return RedisConfig.DATABASE_MISS_VALUE_COUNT;
        }
    }
}
