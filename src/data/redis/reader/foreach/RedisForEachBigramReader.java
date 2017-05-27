package data.redis.reader.foreach;


import data.ReadBigramAction;
import data.models.BigramInformation;
import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;

import java.util.Map.Entry;

public class RedisForEachBigramReader extends RedisForEachReader {
    private double occurrenceCount;
    private ReadBigramAction readBigramAction;

    public RedisForEachBigramReader(ReadBigramAction readBigramAction) {
        Jedis jedis = RedisConfig.jedis;
        occurrenceCount = Double.parseDouble(jedis.get(RedisConfig.BIGRAMS_OCCURRENCE_COUNT));
        this.readBigramAction = readBigramAction;
    }

    @Override
    String getHashMapName() {
        return RedisConfig.BIGRAMS;
    }

    @Override
    void readEntry(Entry<String, String> entry) {
        BigramInformation bigramInformation = new BigramInformation();
        String[] parts = entry.getKey().split(RedisConfig.BIGRAM_SEPARATOR);

        bigramInformation.firstWord = parts[0];
        bigramInformation.secondWord = parts[1];
        bigramInformation.frequency = Double.parseDouble(entry.getValue())/occurrenceCount;

        readBigramAction.read(bigramInformation);
    }
}
