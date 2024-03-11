//You are supposed to add your comments

import java.util.*;

class AIplayer {
    List<Point> availablePoints; // List to store available points
    List<PointsAndScores> rootsChildrenScores; // List to store scores
    Board b = new Board();
    public AIplayer() {
    }

    // Returns the best move among available points
    public Point returnBestMove() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < rootsChildrenScores.size(); ++i) {
            if (MAX < rootsChildrenScores.get(i).score) {
                MAX = rootsChildrenScores.get(i).score;
                best = i;
            }
        }
        return rootsChildrenScores.get(best).point;
    }

    // Returns the minimum value from a list of integers
    public int returnMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        // Iterating through the list to find the minimum value and its index
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    // Returns the maximum value from a list of integers
    public int returnMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        // Iterating throuh the list to find the maximum value and its index
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }


    public void callMinimax(int depth, int turn, Board b){ // Initiates the minimax algorithm
        rootsChildrenScores = new ArrayList<>();
        minimax(depth, turn, b);
    }

    public int minimax(int depth, int turn, Board b) {
        if (depth == 6 || b.isGameOver()) { // If the maximum depth is reached or the game is over, return 0
            return 0;
        }
        // Check win condition for both players
        if (b.hasXWon()) return 1;
        if (b.hasOWon()) return -1;
        List<Point> pointsAvailable = b.getAvailablePoints();
        if (pointsAvailable.isEmpty()) return 0;

        List<Integer> scores = new ArrayList<>();

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);

            if (turn == 1) {
                b.placeAMove(point, 1);
                int currentScore = minimax(depth + 1, 2, b);
                scores.add(currentScore);
                if (depth == 0)
                    rootsChildrenScores.add(new PointsAndScores(currentScore, point));

            } else if (turn == 2) {
                b.placeAMove(point, 2);
                scores.add(minimax(depth + 1, 1, b));
            }
            b.placeAMove(point, 0);
        }
        return turn == 1 ? returnMax(scores) : returnMin(scores);
    }
}
