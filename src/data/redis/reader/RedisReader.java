package data.redis.reader;


import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map.Entry;

public abstract class RedisReader {
    private Jedis jedis = RedisConfig.jedis;

    abstract String getHashMapWordsName();
    abstract double getOverallOccurrenceCount();

    public double read(String value) {
        double occurrenceCount = getValueOccurrenceCount(value);
        return occurrenceCount/getOverallOccurrenceCount();
    }

    private double getValueOccurrenceCount(String value) {
        return Double.parseDouble(jedis.hget(getHashMapWordsName(), value));
    }

    public void readForEach(RedisReadAction redisReadAction) {
        String cursor = redis.clients.jedis.ScanParams.SCAN_POINTER_START;
        ScanParams scanParams = new ScanParams().count(100);
        System.out.println(cursor);
        do {
            ScanResult<Entry<String, String>> scanResult = jedis.hscan(getHashMapWordsName(), cursor, scanParams);
            List<Entry<String, String>> result = scanResult.getResult();

            readFromRedisData(result, redisReadAction);

            cursor = scanResult.getStringCursor();
        } while (!cursor.equals("0"));
    }

    private void readFromRedisData(List<Entry<String, String>> redisData, RedisReadAction redisReadAction) {
        for(Entry<String, String> entry: redisData) {
            double frequency = Double.parseDouble(entry.getValue())/getOverallOccurrenceCount();
            redisReadAction.read(entry.getKey(), frequency, entry.getValue());
        }
    }
}
