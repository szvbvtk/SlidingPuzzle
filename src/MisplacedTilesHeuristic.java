import sac.State;
import sac.StateFunction;

public class MisplacedTilesHeuristic extends StateFunction {
    @Override
    public double calculate(State state) {
        int numberOfMisplacedTiles = 0;
        SlidingPuzzle_ slidingPuzzle = (SlidingPuzzle_) state;
        int n = slidingPuzzle.n;
        int[][] board = slidingPuzzle.board;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != i * n + j && board[i][j] != 0) {
                    numberOfMisplacedTiles++;
                }
            }
        }
        return numberOfMisplacedTiles;
    }
}
