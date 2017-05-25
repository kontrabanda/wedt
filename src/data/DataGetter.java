package data;


import data.filereader.FileReader;
import data.redis.RedisData;
import data.redis.RedisWrapper;
import data.scraper.ScraperData;
import data.scraper.ScraperService;

public class DataGetter {
    private RedisWrapper redisWrapper = RedisWrapper.getInstance();
    private FileReader fileReader;
    private ScraperService scraperService = new ScraperService();

    public DataGetter() {
        fileReader = new FileReader((fileReaderData) -> {
            ScraperData scraperData = scraperService.getDataFromHTMLFile(fileReaderData.file);

            RedisData redisData = new RedisData();
            redisData.key = scraperData.fileName;
            redisData.value = scraperData.value;

            redisWrapper.setData(redisData);
        });
    }

    public void getData() {
        fileReader.readData();
    }
}
