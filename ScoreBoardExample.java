/*
* This program creates a ScoresBoard object to store and manage scores. Each Score object contains a user and a score value.
The ScoresBoard uses a TreeSet to store scores in sorted order, and a ReentrantLock is used to synchronize access to the addScore and printScores
methods to ensure thread safety.
*
* */
import java.util.*;
import java.util.concurrent.locks.*;

class Score implements Comparable<Score> {
    private String user;
    private int scoreValue;

    public Score(String user, int scoreValue) {
        this.user = user;
        this.scoreValue = scoreValue;
    }

    public String getUser() {
        return user;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    @Override
    public int compareTo(Score other) {
        return Integer.compare(other.scoreValue, this.scoreValue);
    }
}

class ScoresBoard {
    private final TreeSet<Score> scores;
    private final Lock lock;

    public ScoresBoard() {
        scores = new TreeSet<>();
        lock = new ReentrantLock();
    }

    public void addScore(Score score) {
        lock.lock();
        try {
            scores.add(score);
        } finally {
            lock.unlock();
        }
    }

    public void printScores() {
        lock.lock();
        try {
            for (Score score : scores) {
                System.out.println(score.getUser() + " " + score.getScoreValue());
            }
        } finally {
            lock.unlock();
        }
    }
}

public class ScoreBoardExample {
    public static void main(String[] args) {
        ScoresBoard scoresBoard = new ScoresBoard();

        // Example input
        String[] input = {
                "user1 2",
                "user3 10",
                "user2 2",
                "user3 10",
                "user3 10"
        };

        // Create Score objects and add them to ScoresBoard
        for (String line : input) {
            String[] parts = line.split(" ");
            String user = parts[0];
            int scoreValue = Integer.parseInt(parts[1]);
            Score score = new Score(user, scoreValue);
            scoresBoard.addScore(score);
        }

        // Print sorted scores
        scoresBoard.printScores();
    }
}
