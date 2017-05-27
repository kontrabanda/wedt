package data.scraper;


import data.models.Bigram;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class ScraperService {
    public ScraperData getData(File inputFile) {
        Document doc = getDocument(inputFile);
        ScraperData scrapedDataModel = new ScraperData();

        scrapedDataModel.fileName = inputFile.getName();
        scrapedDataModel.text = getText(doc);
        scrapedDataModel.words = getWords(scrapedDataModel.text);
        scrapedDataModel.bigrams = getBigrams(scrapedDataModel.text);

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

    private String getText(Document doc) {
        return doc.body().text();
    }

    private String[] getWords(String text) {
        return text.split("\\W+");
    }

    private Bigram[] getBigrams(String text) {
        return null;
    }
}
