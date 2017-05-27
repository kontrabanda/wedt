package data.redis;


import redis.clients.jedis.Jedis;

public class RedisConfig {
    public static Jedis jedis = new Jedis("localhost");
    private RedisConfig() {}
}
