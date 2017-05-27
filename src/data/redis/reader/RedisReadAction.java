package data.redis.reader;


public interface RedisReadAction {
    void read(String word, double frequency, String value);
}
