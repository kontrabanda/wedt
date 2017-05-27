package data;


import data.filereader.FileReader;
import data.models.Bigram;
import data.models.WordInformation;
import data.redis.clear.RedisClear;
import data.redis.reader.RedisBigramReader;
import data.redis.reader.RedisReader;
import data.redis.reader.RedisWordReader;
import data.redis.writer.RedisBigramWriter;
import data.redis.writer.RedisWordsWriter;
import data.scraper.ScraperData;
import data.scraper.ScraperService;

public class DataGetter {
    private RedisWordsWriter redisWordsWriter = new RedisWordsWriter();
    private RedisBigramWriter redisBigramWriter = new RedisBigramWriter();
    private ScraperService scraperService = new ScraperService();
    private RedisWordReader redisWordReader = new RedisWordReader();
    private RedisBigramReader redisBigramReader = new RedisBigramReader();

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

    public double getWordFrequency(String word) {
        return redisWordReader.read(word);
    }

    public double getBigramFrequency(String firstWord, String secondWord) {
        return redisBigramReader.read(firstWord, secondWord);
    }

    public void forEachWord() {
        redisWordReader.readForEach((word, frequency, count) -> {
            System.out.println("Word: " + word + ", frequency: " + frequency + ", count: " + count);
        });
    }

    public void forEachBigram() {
        redisBigramReader.readForEach((bigram, frequency, count) -> {
            System.out.println("Bigram: " + bigram + ", frequency: " + frequency + ", count: " + count);
        });
    }
}
