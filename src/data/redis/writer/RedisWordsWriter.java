package data.redis.writer;


public class RedisWordsWriter extends RedisWriter {
    private final String OVERALL_OCCURRENCE_COUNT = "words_occurrence_count";
    private final String WORDS = "words";

    @Override
    String getOverallOccurrenceCountName() {
        return OVERALL_OCCURRENCE_COUNT;
    }

    @Override
    String getHashMapWords() {
        return WORDS;
    }
}
