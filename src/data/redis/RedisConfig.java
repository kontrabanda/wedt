package data.redis;


import data.properties.PropertiesReader;
import redis.clients.jedis.Jedis;

public class RedisConfig {
    private static PropertiesReader propertiesReader = PropertiesReader.getInstance();
    public static final String BIGRAMS_OCCURRENCE_COUNT = propertiesReader.getValue("BIGRAMS_OCCURRENCE_COUNT");
    public static final String BIGRAMS = propertiesReader.getValue("BIGRAMS");
    public static final String WORDS_OCCURRENCE_COUNT = propertiesReader.getValue("WORDS_OCCURRENCE_COUNT");
    public static final String WORDS = propertiesReader.getValue("WORDS");
    public static final String BIGRAM_SEPARATOR = propertiesReader.getValue("BIGRAM_SEPARATOR");
    public static final double DATABASE_MISS_VALUE_COUNT = Double.parseDouble(propertiesReader.getValue("DATABASE_MISS_VALUE_COUNT"));
    public static Jedis jedis = new Jedis("localhost");
    private RedisConfig() {}
}
