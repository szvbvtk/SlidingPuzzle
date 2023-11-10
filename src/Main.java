import sac.StateImpl;
import sac.graph.AStar;
import sac.graph.GraphSearchAlgorithm;

public class Main {
    public static void main(String[] args) {

        SlidingPuzzle_ puzzle = new SlidingPuzzle_(3);
        puzzle.shuffle(1000);
        System.out.println(puzzle);

        GraphSearchAlgorithm gsa = new AStar(puzzle);
        gsa.execute();

        SlidingPuzzle_ solution = (SlidingPuzzle_) gsa.getSolutions().get(0);
        System.out.println(solution.getMovesAlongPath());

        System.out.println(solution);
    }
}