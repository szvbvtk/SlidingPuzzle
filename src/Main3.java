import sac.graph.AStar;
import sac.graph.BestFirstSearch;
import sac.graph.GraphSearchAlgorithm;
import sac.graph.GraphSearchConfigurator;

public class Main3 {
    public static void main(String[] args) {
        SlidingPuzzle_.setHFunction(new ManhattanHeuristic());
        GraphSearchConfigurator gsc = new GraphSearchConfigurator();

        int[] elapsedTimes = new int[]{0, 0};
        int[] closedStates = new int[]{0, 0};
        int[] pathLength = new int[]{0, 0};


        for (int i = 0; i < 100; i++) {
            SlidingPuzzle_ board = new SlidingPuzzle_(3);
            board.shuffle(1000);

            GraphSearchAlgorithm[] gsa = {new AStar(board, gsc), new BestFirstSearch(board, gsc)};

            for (int j = 0; j < 2; j++) {
                GraphSearchAlgorithm _gsa = gsa[j];
                _gsa.execute();
                SlidingPuzzle_ solution = (SlidingPuzzle_) _gsa.getSolutions().get(0);
                elapsedTimes[j] += (int) _gsa.getDurationTime();
                closedStates[j] += _gsa.getClosedStatesCount();
                pathLength[j] += solution.getPath().size();
            }
        }

        for (int i = 0; i < 2; i++) {
            elapsedTimes[i] /= 100;
            closedStates[i] /= 100;
            pathLength[i] /= 100;
        }

        System.out.println("Astar: średni czas - " + elapsedTimes[0] + "ms; średnia liczba odwiedzanych stanów - " + closedStates[0] + "; średnia długość scieżki - " + pathLength[0]);
        System.out.println("BestFirstSearch: średni czas - " + elapsedTimes[1] + "ms; średnia liczba odwiedzanych stanów - " + closedStates[1] + "; średnia długość scieżki - " + pathLength[1]);
    }
}
