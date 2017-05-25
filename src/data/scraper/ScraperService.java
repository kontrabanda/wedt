package data.scraper;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class ScraperService {
    public ScraperData getDataFromHTMLFile(File inputFile) {
        Document doc = getDocument(inputFile);
        ScraperData scrapedDataModel = new ScraperData();
        scrapedDataModel.value = doc.body().text();

        return scrapedDataModel;
    }

    private Document getDocument(File inputFile) {
        Document doc = null;

        try {
            doc = Jsoup.parse(inputFile, "UTF-8", "http://example.com/");
        } catch(IOException e) {
            e.printStackTrace();
        }

        return doc;
    }
}
