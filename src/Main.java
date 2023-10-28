import sac.StateImpl;
import sac.graph.AStar;
import sac.graph.GraphSearchAlgorithm;

public class Main {
    public static void main(String[] args) {
//        SlidingPuzzle puzzle1 = new SlidingPuzzle(3);
//        puzzle1.shuffle(20);
//
//        System.out.println(puzzle1.toString());
//        System.out.println(puzzle1.isSolution());

        SlidingPuzzle puzzle = new SlidingPuzzle(3);
        puzzle.shuffle(100);
        System.out.println(puzzle);
//        SlidingPuzzle.setHFunction(new MisplacedTilesHeuristic());

        GraphSearchAlgorithm gsa = new AStar(puzzle);
        gsa.execute();

        SlidingPuzzle solution = (SlidingPuzzle) gsa.getSolutions().get(0);
        System.out.println(solution);
    }
}