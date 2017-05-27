package data.redis.writer;


import data.models.Bigram;

public class RedisBigramWriter extends RedisWriter {
    private final String OVERALL_OCCURRENCE_COUNT = "bigram_occurrence_count";
    private final String BIGRAMS = "bigrams";

    @Override
    String getOverallOccurrenceCountName() {
        return OVERALL_OCCURRENCE_COUNT;
    }

    @Override
    String getHashMapWords() {
        return BIGRAMS;
    }

    public void write(Bigram bigram) {
        String value = bigram.firstWord + "_" + bigram.secondWord;
        write(value);
    }
}
