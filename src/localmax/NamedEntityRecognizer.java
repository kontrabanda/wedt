package localmax;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import data.DataGetter;
import data.scraper.ScraperData;
import data.scraper.SimpleScraperService;
import data.filereader.FileReader;

public class NamedEntityRecognizer {
    private SimpleScraperService scraperService = new SimpleScraperService();
    private static DataGetter dataGetter = new DataGetter();
    private double P(String x, String y){
        return dataGetter.getBigramFrequency(x, y);
    }

    private double P(String x){
        return dataGetter.getWordFrequency(x);
    }

    private double p(int x, int y, String[] ngram){
        double p;
        //String phrase="";
        if(x==y) {
            //System.out.println("P("+ngram[x]+")="+P(ngram[x]));
            return P(ngram[x]);
        }
        else{
            p=P(ngram[x]);
            for(int i=x+1; i<=y; ++i) {
                p *= P(ngram[i - 1], ngram[i]);
                //phrase += ngram[i-1]+" ";
            }
            //phrase += ngram[y];
        }
        //System.out.println("P("+phrase+")="+p);
        return p;
    }

    private double gcd(String[] ngram){
        int n = ngram.length;
        double avp = 0;

        for(int i=0; i<n-1; ++i)
            avp += (p(0, i, ngram) * p(i + 1, n-1, ngram)) / (n - 1);
        if(avp == 0) System.out.println("avp=0");

        return Math.pow(p(0, n-1, ngram), 2)/avp;
    }

    public String[] findEntities(){
        String[] temp = null;
        FileReader fileReader = new FileReader((fileReaderData) -> {
            ScraperData scraperData = scraperService.getData(fileReaderData.file);

            String[] strings = scraperData.text.split("[.,:;\\n\\?]");
            for(String text : strings) {
                parse(text);    //zwracane encje zwracać dalej
            }
        });
        fileReader.readData();

        return temp;
    }

    public List<String> parse(String text){
        System.out.println("[parsing] "+text);
        //System.out.println("");
        List<String> temp = new ArrayList();
        String[] ngram = text.split("\\W+");
        int n = ngram.length;
        double[][] gcds = new double[n][n+1]; //pierwszy: początek ngramu, drugi: długość ngramu

        for(int start = 0; start < n-1; ++start){
            //System.out.print(ngram[start]+" ");
            if(ngram[start].length() == 0) {
                //System.out.println("");
                continue;
            }
            if(!Character.isUpperCase(ngram[start].charAt(0))){
                //System.out.println("");
                continue;
            }

            for(int len=2; len<=n-start; ++len){
                String[] temp_ngram = Arrays.copyOfRange(ngram, start, start+len);
                if(!Character.isUpperCase(temp_ngram[0].charAt(0))) {
                    //System.out.println("");
                    break;
                }
                gcds[start][len] = gcd(temp_ngram);
                if(len>2) {
                    if(gcds[start][len] / gcds[start][len - 1] < 1.0/70.0){

                        temp_ngram = Arrays.copyOfRange(ngram, start, start+len-1);
                        String phrase = String.join(" ", temp_ngram);
                        temp.add(phrase);
                        //System.out.println("gcdratio: "+gcds[start][len] / gcds[start][len - 1]);
                        System.out.println("[Detected] "+phrase);
                        start += len-1;
                        break;
                    }
                    /*else
                        System.out.println("gcdratio: "+gcds[start][len] / gcds[start][len - 1]);*/
                }
                //System.out.print(gcds[start][len]+" ");
            }
            //System.out.println("");
        }
        //tutaj posprawdzać gcds i porobić z tego listę wykrytych encji

        return temp;
    }
}