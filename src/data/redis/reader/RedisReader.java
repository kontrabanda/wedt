package data.redis.reader;


import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;

public abstract class RedisReader {
    private Jedis jedis = RedisConfig.jedis;

    abstract String getHashMapName();
    abstract double getOverallOccurrenceCount();

    public double read(String value) {
        double occurrenceCount = getValueOccurrenceCount(value);
        return occurrenceCount/getOverallOccurrenceCount();
    }

    private double getValueOccurrenceCount(String value) {
        return Double.parseDouble(jedis.hget(getHashMapName(), value));
    }


}
