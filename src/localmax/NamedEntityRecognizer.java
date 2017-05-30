package localmax;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import data.DataGetter;
import data.filereader.FileReaderData;
import data.properties.PropertiesReader;
import data.scraper.ScraperData;
import data.scraper.SimpleScraperService;
import data.filereader.FileReader;
import data.filewriter.*;
import java.lang.StringBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NamedEntityRecognizer {
    public static Logger logger = Logger.getLogger(NamedEntityRecognizer.class.getName());
    private PropertiesReader propertiesReader = PropertiesReader.getInstance();
    private final double threshold = Double.parseDouble(propertiesReader.getValue("THRESHOLD"));
    private final String writer_name = propertiesReader.getValue("WRITER_FILE_NAME");

    private SimpleScraperService scraperService = new SimpleScraperService();
    private static DataGetter dataGetter = new DataGetter();

    public void findEntities(){
        FileReader fileReader = new FileReader((fileReaderData) -> {
            String[] sentences = getSentenceFromFile(fileReaderData);
            StringBuilder namedEntities = getAllNamedEntities(sentences);

            writeToFile(fileReaderData, namedEntities.toString());
        });

        fileReader.readData();
    }

    private String[] getSentenceFromFile(FileReaderData fileReaderData) {
        ScraperData scraperData = scraperService.getData(fileReaderData.file);
        return scraperData.text.split("[.,:;\\n\\?]");
    }

    private void writeToFile(FileReaderData fileReaderData, String data) {
        FileWriter fileWriter = new FileWriter();
        FileWriterData fwd = new FileWriterData();

        fwd.path = fileReaderData.dirPath;
        fwd.filename = writer_name;
        fwd.data = data;

        fileWriter.writeData(fwd);
    }

    private StringBuilder getAllNamedEntities(String[] sentences) {
        StringBuilder out = new StringBuilder();

        for(String singleSentence : sentences) {
            out = getNamedEntitiesFromSentence(singleSentence, out);
        }

        return out;
    }

    private StringBuilder getNamedEntitiesFromSentence(String sentence, StringBuilder out) {
        List<String> entities = parse(sentence);
        EntityFilter entityFilter = new EntityFilter(entities);
        entities = entityFilter.getEntitiesFilteredByCapitalLetters();

        for(String singleEntity : entities) {
            out.append(singleEntity);
            out.append("\n");
        }

        return out;
    }

    private List<String> parse(String text){
        List<String> temp = new ArrayList();
        String[] ngram = text.split("\\W+");
        int n = ngram.length;
        double[][] gcds = new double[n][n+1];

        for(int start = 0; start < n-1; ++start) {
            if(ngram[start].length() == 0) {
                continue;
            }
            if(!Character.isUpperCase(ngram[start].charAt(0))) {
                continue;
            }

            for(int len=2; len<=n-start; ++len){
                String[] temp_ngram = Arrays.copyOfRange(ngram, start, start+len);
                if(!Character.isUpperCase(temp_ngram[0].charAt(0))) {
                    break;
                }
                gcds[start][len] = gcd(temp_ngram);
                if(len>3 || (len>2 && start+len==n)) {
                    if(gcds[start][len] / gcds[start][len - 1] < threshold){

                        temp_ngram = Arrays.copyOfRange(ngram, start, start+len-1);
                        String phrase = String.join(" ", temp_ngram);
                        temp.add(phrase);
                        start += len-1;
                        break;
                    }
                }
            }
        }

        return temp;
    }

    private double gcd(String[] ngram){
        int n = ngram.length;
        double avp = 0;

        for(int i=0; i<n-1; ++i)
            avp += (p(0, i, ngram) * p(i + 1, n-1, ngram)) / (n - 1);
        if(avp == 0) System.out.println("avp=0");

        return Math.pow(p(0, n-1, ngram), 2)/avp;
    }

    private double P(String x, String y){
        return dataGetter.getBigramFrequency(x, y);
    }

    private double P(String x){
        return dataGetter.getWordFrequency(x);
    }

    private double p(int x, int y, String[] ngram){
        double p;

        if(x==y) {

            return P(ngram[x]);
        }
        else{
            p=P(ngram[x]);
            for(int i=x+1; i<=y; ++i) {
                p *= P(ngram[i - 1], ngram[i]);
            }
        }

        return p;
    }
}