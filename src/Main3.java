import sac.graph.AStar;
import sac.graph.BestFirstSearch;
import sac.graph.GraphSearchAlgorithm;
import sac.graph.GraphState;

public class Main3 {
    public static void main(String[] args) {
        SlidingPuzzle_.setHFunction(new ManhattanHeuristic());

        int[] elapsedTimes = new int[]{0, 0};
        int[] closedStates = new int[]{0, 0};
        int[] pathLength = new int[]{0, 0};


        for(int i = 0; i < 100; i++) {
            SlidingPuzzle_ board = new SlidingPuzzle_(3);
            board.shuffle(1000);

            GraphSearchAlgorithm[] gsa = {new AStar(board), new BestFirstSearch()};

            for(int j = 0; j < 2; j++) {
                GraphSearchAlgorithm _gsa = gsa[j];
                _gsa.execute();
                elapsedTimes[j] += _gsa.getDurationTime();
                SlidingPuzzle_ solution = (SlidingPuzzle_) _gsa.getSolutions().get(0);
                closedStates[j] += _gsa.getClosedStatesCount();
            }
        }

        elapsedTimes[0] /= 100;
        elapsedTimes[1] /= 100;
        closedStates[0] /= 100;
        closedStates[1] /= 100;
        pathLength[0] /= 100;
        pathLength[1] /= 100;

        System.out.println("Astar: czas - " + elapsedTimes[0] + " liczba odwiedzanych stanów - " + elapsedTimes[0]);
        System.out.println("BestFirstSearch: czas - " + elapsedTimes[1] + " liczba odwiedzanych stanów - " + elapsedTimes[1]);
    }
}
