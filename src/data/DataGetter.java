package data;


import data.filereader.FileReader;
import data.models.Bigram;
import data.redis.clear.RedisClear;
import data.redis.reader.RedisReader;
import data.redis.writer.RedisBigramWriter;
import data.redis.writer.RedisWordsWriter;
import data.scraper.ScraperData;
import data.scraper.ScraperService;

public class DataGetter {
    private RedisWordsWriter redisWordsWriter = new RedisWordsWriter();
    private RedisBigramWriter redisBigramWriter = new RedisBigramWriter();
    private ScraperService scraperService = new ScraperService();

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


    public void readData() {
        RedisReader redisReader = new RedisReader();

        redisReader.readForEach((word, frequency, count) -> {
            System.out.println("Word: " + word + ", count: " + count);
        });
    }
}
