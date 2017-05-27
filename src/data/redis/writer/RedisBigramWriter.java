package data.redis.writer;


import data.models.Bigram;
import data.redis.RedisConfig;

public class RedisBigramWriter extends RedisWriter {
    @Override
    String getOverallOccurrenceCountName() {
        return RedisConfig.BIGRAMS_OCCURRENCE_COUNT;
    }

    @Override
    String getHashMapWordsName() {
        return RedisConfig.BIGRAMS;
    }

    public void write(Bigram bigram) {
        String value = bigram.firstWord + "_" + bigram.secondWord;
        write(value);
    }
}
