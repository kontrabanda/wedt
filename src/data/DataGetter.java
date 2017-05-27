package data;


import data.filereader.FileReader;
import data.models.Bigram;
import data.redis.clear.RedisClear;
import data.redis.reader.RedisBigramReader;
import data.redis.reader.RedisWordReader;
import data.redis.reader.foreach.RedisForEachBigramReader;
import data.redis.reader.foreach.RedisForEachWordReader;
import data.redis.writer.RedisBigramWriter;
import data.redis.writer.RedisWordsWriter;
import data.scraper.ScraperData;
import data.scraper.ScraperService;

public class DataGetter {
    private RedisWordsWriter redisWordsWriter = new RedisWordsWriter();
    private RedisBigramWriter redisBigramWriter = new RedisBigramWriter();
    private ScraperService scraperService = new ScraperService();

    public void clearData() {
        RedisClear redisClear = new RedisClear();
        redisClear.clear();
    }

    public void writeData() {
        FileReader fileReader = new FileReader((fileReaderData) -> {
            ScraperData scraperData = scraperService.getData(fileReaderData.file);

            saveWords(scraperData.words);
            saveBigrams(scraperData.bigrams);
        });
        fileReader.readData();
    }

    private void saveWords(String[] words) {
        for(String singleWord: words) {
            redisWordsWriter.write(singleWord);
        }
    }

    private void saveBigrams(Bigram[] bigrams) {
        for(Bigram singleBigram: bigrams) {
            redisBigramWriter.write(singleBigram);
        }
    }

    public double getWordFrequency(String word) {
        RedisWordReader redisWordReader = new RedisWordReader();
        return redisWordReader.read(word);
    }

    public double getBigramFrequency(String firstWord, String secondWord) {
        RedisBigramReader redisBigramReader = new RedisBigramReader();
        return redisBigramReader.read(firstWord, secondWord);
    }

    public void forEachWord(ReadWordAction readWordAction) {
        RedisForEachWordReader redisForEachWordReader = new RedisForEachWordReader(readWordAction);
        redisForEachWordReader.readForEach();
    }

    public void forEachBigram(ReadBigramAction readBigramAction) {
        RedisForEachBigramReader redisForEachBigramReader = new RedisForEachBigramReader(readBigramAction);
        redisForEachBigramReader.readForEach();
    }
}
