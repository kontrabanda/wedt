import data.DataGetter;
import localmax.NamedEntityRecognizer;

import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<String> actions = Arrays.asList(args);


        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);


        if(actions.contains("heuristic")) {
            runHeuristicAlgorithm();
        }

        if(actions.contains("clear-data")) {
            runClearData();
        }

        if(actions.contains("write-data")) {
            runWriteData();
        }

        if(actions.contains("read-data")) {
            runReadData();
        }

        if(actions.contains("localmaxs")) {
            runLocalMaxsAlgorithm();
        }
    }

    private static void runHeuristicAlgorithm() {
        System.out.println("runHeuristicAlgorithm");
        //HeuristicAlgorithmWrapper heuristicAlgorithmWrapper = new HeuristicAlgorithmWrapper();
        //heuristicAlgorithmWrapper.runHeuristic();
    }

    private static void runClearData() {
        System.out.println("runClearData");
        DataGetter dataGetter = new DataGetter();
        dataGetter.clearData();
    }

    private static void runWriteData() {
        System.out.println("runWriteData");
        DataGetter dataGetter = new DataGetter();
        dataGetter.writeData();
    }

    private static void runReadData() {
        System.out.println("readData");
        DataGetter dataGetter = new DataGetter();
        dataGetter.forEachWord(wordInformation -> {
            //Tutaj przetwarzanie każdego słowa
        });
        dataGetter.forEachBigram(bigramInformation -> {
            //Tutaj przetwarzanie każdego bigramu
        });
    }

    private static void runLocalMaxsAlgorithm() {
        System.out.println("runLocalMaxsAlgorithm");
        NamedEntityRecognizer ner = new NamedEntityRecognizer();
        /*ner.parse("Frequently Asked Questions\n" +
                "Question: What's the requirement of the paper format?\n");*/
        ner.findEntities();
    }
}
