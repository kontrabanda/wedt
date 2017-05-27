package data.redis.writer;


import data.redis.RedisConfig;

public class RedisWordsWriter extends RedisWriter {
    @Override
    String getOverallOccurrenceCountName() {
        return RedisConfig.WORDS_OCCURRENCE_COUNT;
    }

    @Override
    String getHashMapWordsName() {
        return RedisConfig.WORDS;
    }
}
