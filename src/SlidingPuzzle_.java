import sac.graph.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlidingPuzzle_ extends GraphStateImpl {
    final int[][] board;
    final int n;
    int emptyCellRow;
    int emptyCellCol;
    Random random = new Random();


    public enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    public SlidingPuzzle_(int n) {
        this.board = new int[n][n];
        this.n = n;
        this.emptyCellRow = 0;
        this.emptyCellCol = 0;

//        for (int i = 0; i < n * n; i++) {
//            this.board[i / n][i % n] = i;
//        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = i * n + j;
            }
        }

    }

    public SlidingPuzzle_(SlidingPuzzle_ SlidingPuzzle_) {
        this.n = SlidingPuzzle_.n;
        this.board = new int[this.n][this.n];
        this.emptyCellRow = SlidingPuzzle_.emptyCellRow;
        this.emptyCellCol = SlidingPuzzle_.emptyCellCol;

        for (int i = 0; i < n; i++) {
            this.board[i] = java.util.Arrays.copyOf(SlidingPuzzle_.board[i], n);
        }
    }

    public boolean isPositionValid(int row, int col) {
        if (row < 0 || row >= this.n || col < 0 || col >= this.n) {
            return false;
        }
        return true;
    }


    public boolean moveSinglePiece(Direction direction) {
        int newEmptyCellCol = this.emptyCellCol;
        int newEmptyCellRow = this.emptyCellRow;

        switch (direction) {
            case UP:
                --newEmptyCellRow;
                break;
            case DOWN:
                ++newEmptyCellRow;
                break;
            case LEFT:
                --newEmptyCellCol;
                break;
            case RIGHT:
                ++newEmptyCellCol;
                break;
        }

        boolean positionValid = isPositionValid(newEmptyCellRow, newEmptyCellCol);

        if (positionValid) {
            int slidingPieceValue = board[newEmptyCellRow][newEmptyCellCol];
            this.board[emptyCellRow][emptyCellCol] = slidingPieceValue;
            this.emptyCellRow = newEmptyCellRow;
            this.emptyCellCol = newEmptyCellCol;

            this.board[emptyCellRow][emptyCellCol] = 0;

//            this.setMoveName(direction.name());

            return true;
        }
        return false;
    }

    public void shuffle(int numberOfMoves) {

        for (int i = 0; i < numberOfMoves; i++) {
            int randomNumber = this.random.nextInt(Direction.values().length);
            Direction randomDirection = Direction.values()[randomNumber];
            moveSinglePiece(randomDirection);
        }
    }

    @Override
    public boolean isSolution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.board[i][j] != i * n + j) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<GraphState> generateChildren() {
        List<GraphState> children = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            SlidingPuzzle_ child = new SlidingPuzzle_(this);
            boolean moveValid = child.moveSinglePiece(Direction.values()[i]);

            if (moveValid) {
                child.setMoveName(Direction.values()[i].name());
                children.add(child);
            }
        }

        return children;
    }


    public String toString() {
        StringBuilder puzzleString = new StringBuilder();

        puzzleString.append(String.valueOf('-').repeat(this.n * 3)).append('\n');
        for (int i = 0; i < n; i++) {
            puzzleString.append("| ");
            for (int j = 0; j < n; j++) {
                puzzleString.append(this.board[i][j]).append(' ');
            }
            puzzleString.append("|\n");
        }
        puzzleString.append(String.valueOf('-').repeat(this.n * 3)).append('\n');

        return puzzleString.toString();
    }

    public int hashCode() {
        return this.toString().hashCode();
    }
}
