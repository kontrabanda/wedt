package data.redis;


public class RedisWrapper {
    private static RedisWrapper instance = null;
    private RedisWrapper() {}

    public void setData(String data) {
        System.out.println("*********************RedisWrapper************************");
        System.out.println(data);
        System.out.println("*********************RedisWrapper************************");
    }

    public static RedisWrapper getInstance() {
        if(instance == null) {
            instance = new RedisWrapper();
        }

        return instance;
    }
}
