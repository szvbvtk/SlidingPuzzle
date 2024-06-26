import sac.StateImpl;
import sac.graph.AStar;
import sac.graph.GraphSearchAlgorithm;

import java.util.Random;

public class Main2 {
    public static void main(String[] args) {

        long timeElapsedManhattan = 0;
        long timeElapsedMisplacedTiles = 0;
        int closedStatesManhattan = 0;
        int closedStatesMisplacedTiles = 0;

        for (int i = 0; i < 100; i++) {
            SlidingPuzzle_ board = new SlidingPuzzle_(3);
            board.shuffle(1000);

            GraphSearchAlgorithm gsaMT = new AStar(board);
            GraphSearchAlgorithm gsaMH = new AStar(board);

            SlidingPuzzle_.setHFunction(new MisplacedTilesHeuristic());
            gsaMT.execute();
            timeElapsedMisplacedTiles += gsaMT.getDurationTime();
            closedStatesMisplacedTiles += gsaMT.getClosedStatesCount();

            SlidingPuzzle_.setHFunction(new ManhattanHeuristic());
            gsaMH.execute();
            timeElapsedManhattan += gsaMH.getDurationTime();
            closedStatesManhattan += gsaMH.getClosedStatesCount();

        }

        timeElapsedManhattan /= 100;
        timeElapsedMisplacedTiles /= 100;
        closedStatesManhattan /= 100;
        closedStatesMisplacedTiles /= 100;

        System.out.println("Średni czas MisplacedTiles: " + timeElapsedMisplacedTiles + " ms;\tśrednia liczba odwiedzonych stanów: " + closedStatesMisplacedTiles);
        System.out.println("Średni czas Manhattan: " + timeElapsedManhattan + " ms;\tśrednia liczba odwiedzonych stanów: " + closedStatesManhattan);
    }
}
