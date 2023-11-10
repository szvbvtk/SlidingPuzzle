import sac.State;
import sac.StateFunction;

public class ManhattanHeuristic extends StateFunction {
    @Override
    public double calculate(State state) {
        SlidingPuzzle_ slidingPuzzle = (SlidingPuzzle_) state;
        int n = slidingPuzzle.n;
        int emptyCellRow = slidingPuzzle.emptyCellRow;
        int emptyCellCol = slidingPuzzle.emptyCellCol;
        int[][] board = slidingPuzzle.board;

        int accumulatedDistance = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == emptyCellRow && j == emptyCellCol)
                    continue;

                accumulatedDistance += manhattanDistance(n, i, j, board[i][j]);

            }
        }

        return accumulatedDistance;
    }

    private int manhattanDistance(int n, int i_pos, int j_pos, int tileIndex) {
        int goal_i = (int) (tileIndex / n);
        int goal_j = tileIndex % n;

        return Math.abs(goal_i - i_pos) + Math.abs(goal_j - j_pos);
    }
}
