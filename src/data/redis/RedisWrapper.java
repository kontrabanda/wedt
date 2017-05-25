package data.redis;


import redis.clients.jedis.Jedis;

public class RedisWrapper {
    private Jedis jedis = new Jedis("localhost");
    private final String SCRAPPED_TEXT = "scrapped:text";
    private static RedisWrapper instance = null;
    private RedisWrapper() {}

    public void setData(RedisData data) {
        jedis.hset(SCRAPPED_TEXT, data.key, data.value);
    }

    public static RedisWrapper getInstance() {
        if(instance == null) {
            instance = new RedisWrapper();
        }

        return instance;
    }
}
