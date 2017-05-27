import data.DataGetter;

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

        if(actions.contains("get-data")) {
            runGetData();
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

    private static void runGetData() {
        System.out.println("runGetData");
        DataGetter dataGetter = new DataGetter();
        dataGetter.getData();
    }

    private static void runLocalMaxsAlgorithm() {
        System.out.println("runLocalMaxsAlgorithm");
    }
}
