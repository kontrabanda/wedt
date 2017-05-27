package data.redis.reader.foreach;


import data.ReadWordAction;
import data.models.WordInformation;
import data.redis.RedisConfig;
import redis.clients.jedis.Jedis;

import java.util.Map.Entry;

public class RedisForEachWordReader extends RedisForEachReader {
    private double occurrenceCount;
    private ReadWordAction readWordAction;

    public RedisForEachWordReader(ReadWordAction readWordAction) {
        Jedis jedis = RedisConfig.jedis;
        occurrenceCount = Double.parseDouble(jedis.get(RedisConfig.WORDS_OCCURRENCE_COUNT));
        this.readWordAction = readWordAction;
    }

    @Override
    String getHashMapName() {
        return RedisConfig.WORDS;
    }

    @Override
    void readEntry(Entry<String, String> entry) {
        WordInformation wordInformation = new WordInformation();

        wordInformation.word = entry.getKey();
        wordInformation.frequency = Double.parseDouble(entry.getValue())/occurrenceCount;

        readWordAction.read(wordInformation);
    }
}
