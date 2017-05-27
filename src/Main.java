//import data.FileReader;

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

        if(actions.contains("localmaxs")) {
            runLocalMaxsAlgorithm();
        }
    }

    private static double P(String x, String y){
        return 0.2;
    }

    private static double P(String x){
        return 0.1;
    }

    private static double p(int x, int y, String[] ngram){
        double p;
        if(x==y)
            return P(ngram[x]);
        else{
            p=P(ngram[x]);
            for(int i=x+1; i<=y; ++i)
                p *= P(ngram[i], ngram[i-1]);
        }
        return p;
    }

    private static double gcd(String[] ngram){
        int n = ngram.length-1;
        double avp = 0;

        for(int i=0; i<n; ++i)
            avp += (p(1, i, ngram) * p(i + 1, n, ngram)) / (n - 1);

        return Math.pow(p(1, n, ngram), 2)/avp;
    }

    private static void parse(String text){
        String[] ngram = text.split("\\s");
        int n = ngram.length;
        double[][] gcds = new double[n][n+1]; //pierwszy: początek ngramu, drugi: długość ngramu
        for(int start = 0; start < n-1; ++start){
            for(int len=2; len<=n-start; ++len){
                gcds[start][len] = gcd(Arrays.copyOfRange(ngram, start, start+len));
            }
        }
    }

    private static void runHeuristicAlgorithm() {
        System.out.println("runHeuristicAlgorithm");
        //HeuristicAlgorithmWrapper heuristicAlgorithmWrapper = new HeuristicAlgorithmWrapper();
        //heuristicAlgorithmWrapper.runHeuristic();
    }

    private static void runLocalMaxsAlgorithm() {
        System.out.println("runLocalMaxsAlgorithm");

    }
}
