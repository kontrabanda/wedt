package data.redis.writer;


import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;

abstract public class RedisWriter {
    private Jedis jedis = RedisConfig.jedis;

    RedisWriter() {
        jedis.set(getOverallOccurrenceCountName(), "0");
        jedis.del(getHashMapWords());
    }

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
        return jedis.hget(getHashMapWords(), value) != null;
    }

    private void writeNewValue(String value) {
        jedis.hset(getHashMapWords(), value, "1");
    }

    private void incrementValueOccurrenceCount(String value) {
        jedis.hincrBy(getHashMapWords(), value, 1);
    }

    abstract String getOverallOccurrenceCountName();
    abstract String getHashMapWords();
}
