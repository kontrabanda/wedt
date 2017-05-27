package data.redis.reader;


import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map.Entry;

public class RedisReader {
    private Jedis jedis = RedisConfig.jedis;
    private double occurrenceCount;

    public RedisReader() {
        occurrenceCount = Double.parseDouble(jedis.get(RedisConfig.WORDS_OCCURRENCE_COUNT));
    }

    String getHashMapWordsName() {
        return RedisConfig.WORDS;
    }

    double getOverallOccurrenceCount() {
        return occurrenceCount;
    }

    public double read(String value) {
        double occurrenceCount = getValueOccurrenceCount(value);
        return occurrenceCount/getOverallOccurrenceCount();
    }

    private double getValueOccurrenceCount(String value) {
        return Double.parseDouble(jedis.hget(getHashMapWordsName(), value));
    }

    public void readForEach(RedisReadWordAction redisReadWordAction) {
        String cursor = redis.clients.jedis.ScanParams.SCAN_POINTER_START;
        ScanParams scanParams = new ScanParams().count(100);
        System.out.println(cursor);
        do {
            ScanResult<Entry<String, String>> scanResult = jedis.hscan(getHashMapWordsName(), cursor, scanParams);
            List<Entry<String, String>> result = scanResult.getResult();

            readFromRedisData(result, redisReadWordAction);

            cursor = scanResult.getStringCursor();
        } while (!cursor.equals("0"));
    }

    private void readFromRedisData(List<Entry<String, String>> redisData, RedisReadWordAction redisReadWordAction) {
        for(Entry<String, String> entry: redisData) {
            //double frequency = getValueOccurrenceCount(entry.getValue());

            redisReadWordAction.read(entry.getKey(), 0, entry.getValue());
        }
    }
}
