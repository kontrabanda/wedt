package data.redis.writer;


import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;

abstract public class RedisWriter {
    private Jedis jedis = RedisConfig.jedis;

    public void write(String value) {
        incrementOccurrenceCount();
        writeValue(value);
    }

    private void incrementOccurrenceCount() {
        jedis.incr(getOverallOccurrenceCountName());
    }

    private void writeValue(String value) {
        if(checkIfValueExists(value)) {
            incrementValueOccurrenceCount(value);
        } else {
            writeNewValue(value);
        }
    }

    private boolean checkIfValueExists(String value) {
        return jedis.hget(getHashMapWordsName(), value) != null;
    }

    private void writeNewValue(String value) {
        jedis.hset(getHashMapWordsName(), value, "1");
    }

    private void incrementValueOccurrenceCount(String value) {
        jedis.hincrBy(getHashMapWordsName(), value, 1);
    }

    abstract String getOverallOccurrenceCountName();
    abstract String getHashMapWordsName();
}
