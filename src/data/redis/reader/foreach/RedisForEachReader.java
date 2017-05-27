package data.redis.reader.foreach;


import data.redis.RedisConfig;
import data.redis.reader.RedisReadAction;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;

abstract public class RedisForEachReader {
    private Jedis jedis = RedisConfig.jedis;

    abstract String getHashMapName();
    abstract void readEntry(Map.Entry<String, String> entry);

    public void readForEach() {
        String cursor = redis.clients.jedis.ScanParams.SCAN_POINTER_START;
        ScanParams scanParams = new ScanParams().count(100);

        do {
            ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(getHashMapName(), cursor, scanParams);
            List<Map.Entry<String, String>> result = scanResult.getResult();

            readFromRedisData(result);

            cursor = scanResult.getStringCursor();
        } while (!cursor.equals("0"));
    }

    private void readFromRedisData(List<Map.Entry<String, String>> redisData) {
        for(Map.Entry<String, String> entry: redisData) {
            readEntry(entry);
        }
    }
}
