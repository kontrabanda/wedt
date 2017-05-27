package data.redis.reader;


public interface RedisReadWordAction {
    void read(String word, double frequency, String value);
}
