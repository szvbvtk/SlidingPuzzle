import sac.StateImpl;
import sac.graph.AStar;
import sac.graph.GraphSearchAlgorithm;

public class Main {
    public static void main(String[] args) {
//        SlidingPuzzle_ puzzle1 = new SlidingPuzzle_(3);
//        puzzle1.shuffle(20);
//
//        System.out.println(puzzle1.toString());
//        System.out.println(puzzle1.isSolution());

        SlidingPuzzle_ puzzle = new SlidingPuzzle_(4);
        puzzle.shuffle(120);
        System.out.println(puzzle);
//        SlidingPuzzle_.setHFunction(new MisplacedTilesHeuristic());
        SlidingPuzzle_.setHFunction(new ManhattanHeuristic());

        GraphSearchAlgorithm gsa = new AStar(puzzle);
        gsa.execute();

        SlidingPuzzle_ solution = (SlidingPuzzle_) gsa.getSolutions().get(0);
        System.out.println(solution);
    }
}