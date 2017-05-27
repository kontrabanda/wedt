package data.scraper;


import data.models.Bigram;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;

public class ScraperService {
    private static int badDocumentCounter;
    private static int documentCounter;

    public ScraperData getData(File inputFile) {
        Document doc = getDocument(inputFile);
        ScraperData scrapedDataModel = new ScraperData();

        documentStatistic(doc);

        scrapedDataModel.fileName = inputFile.getName();
        scrapedDataModel.text = getText(doc);
        scrapedDataModel.words = getWords(scrapedDataModel.text);
        scrapedDataModel.bigrams = getBigrams(scrapedDataModel.words);

        return scrapedDataModel;
    }

    private void documentStatistic(Document doc) {
        ++documentCounter;

        if(doc == null) {
            ++badDocumentCounter;

            System.out.println("*******************************************");
            System.out.println("Document counter: " + documentCounter);
            System.out.println("Bad document counter: " + badDocumentCounter);
            System.out.println("*******************************************");
        }
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
        return (doc != null && doc.body() != null) ? doc.body().text() : "";
    }

    private String[] getWords(String text) {
        return text.split("\\W+");
    }

    private Bigram[] getBigrams(String[] words) {
        int bigramCount = words.length - 1;
        Bigram[] result = new Bigram[bigramCount];

        for(int i = 0; i < bigramCount; ++i) {
            result[i] = Bigram.of(words[i], words[i+1]);
        }

        return result;
    }
}
