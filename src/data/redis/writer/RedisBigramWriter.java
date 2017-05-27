package data.redis.writer;


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
}
