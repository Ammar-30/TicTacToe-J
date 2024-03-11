//You are supposed to add your comments

import java.util.*;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + (x+1) + ", " + (y+1) + "]";
    }
}

class PointsAndScores {
    int score;
    Point point;

    PointsAndScores(int score, Point point) {
        this.score = score;
        this.point = point;
    }
}

class Board {
    List<Point> availablePoints;
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[5][5]; // swaped the value 3x3 to 5x5 for bigger board specification

    public Board() {
    }

    // Checks if the game is over by determining if X or O has won
    public boolean isGameOver() {
        return (hasXWon() || hasOWon() || getAvailablePoints().isEmpty());
    }

    public boolean hasXWon() { // This funtion checks for all possible positions where X can win and returns true else returns false which means it didn't win
        for (int i = 0; i < 5; ++i) {
            // These positions check for diagonals
            if (board[i][i] == 1 && board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == board[3][3] && board[0][0] == board[4][4]) {
                return true;
            }
            if (board[i][4 - i] == 1 && board[0][4] == board[1][3] && board[0][4] == board[2][2] && board[0][4] == board[3][1] && board[0][4] == board[4][0]) {
                return true;
            }
            // These positions check for rows and columns
            if ((board[i][0] == 1 && board[i][1] == 1 && board[i][2] == 1 && board[i][3] == 1 && board[i][4] == 1) ||
                    (board[0][i] == 1 && board[1][i] == 1 && board[2][i] == 1 && board[3][i] == 1 && board[4][i] == 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasOWon() { // This funtion checks for all possible positions where O can win and returns true else returns false which means it didn't win
        for (int i = 0; i < 5; ++i) {
            // These positions check for diagonals
            if (board[i][i] == 2 && board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == board[3][3] && board[0][0] == board[4][4]) {
                return true;
            }
            if (board[i][4 - i] == 2 && board[0][4] == board[1][3] && board[0][4] == board[2][2] && board[0][4] == board[3][1] && board[0][4] == board[4][0]) {
                return true;
            }
            // These positions check for rows and columns
            if ((board[i][0] == 2 && board[i][1] == 2 && board[i][2] == 2 && board[i][3] == 2 && board[i][4] == 2) ||
                    (board[0][i] == 2 && board[1][i] == 2 && board[2][i] == 2 && board[3][i] == 2 && board[4][i] == 2)) {
                return true;
            }
        }
        return false;
    }

    //shows a list of available points on the game board
    public List<Point> getAvailablePoints() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }
    // Gets the state
    public int getState(Point point) {
        return board[point.x][point.y];
    }

    // Places a move (X or O)
    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;
    }

    public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 5; ++i) { // Changed the outer loop limit from 3 to 5 to display a bigger board and so did for the inner loop (rest remains same)
            for (int j = 0; j < 5; ++j) {
                if (board[i][j] == 1)
                    System.out.print("X ");
                else if (board[i][j] == 2)
                    System.out.print("O ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}
