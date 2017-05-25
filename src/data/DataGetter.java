package data;


import data.filereader.FileReader;
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
            redisWrapper.setData(scraperData.value);
        });
    }

    public void getData() {
        fileReader.readData();
    }
}
