package data.redis;


import redis.clients.jedis.Jedis;

public class RedisConfig {
    public static final String BIGRAMS_OCCURRENCE_COUNT = "bigram_occurrence_count";
    public static final String BIGRAMS = "bigrams";
    public static final String WORDS_OCCURRENCE_COUNT = "words_occurrence_count";
    public static final String WORDS = "words";
    public static final String BIGRAM_SEPARATOR = ":_:";
    public static final double DATABASE_MISS_VALUE_COUNT = 0.5;
    public static Jedis jedis = new Jedis("localhost");
    private RedisConfig() {}
}
