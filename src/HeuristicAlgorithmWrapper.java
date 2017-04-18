import com.simontuffs.onejar.Boot;

class HeuristicAlgorithmWrapper {
    void runHeuristic() {
        try {
            Boot.main(new String[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
