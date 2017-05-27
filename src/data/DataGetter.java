package data;


import data.filereader.FileReader;
import data.models.Bigram;
import data.redis.writer.RedisWordsWriter;
import data.scraper.ScraperData;
import data.scraper.ScraperService;

public class DataGetter {
    private RedisWordsWriter redisWordsWriter = new RedisWordsWriter();
    private FileReader fileReader;
    private ScraperService scraperService = new ScraperService();

    public DataGetter() {
        fileReader = new FileReader((fileReaderData) -> {
            ScraperData scraperData = scraperService.getData(fileReaderData.file);

            saveWords(scraperData.words);
            saveBigrams(scraperData.bigrams);
        });
    }

    private void saveWords(String[] words) {
        for(String singleWord: words) {
            redisWordsWriter.write(singleWord);
        }
    }

    private void saveBigrams(Bigram[] bigrams) {

    }

    public void getData() {
        fileReader.readData();
    }
}
