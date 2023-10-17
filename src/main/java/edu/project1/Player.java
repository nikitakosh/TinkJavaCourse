package edu.project1;

public class Player {
    private int attempts;
    private final String name;

    public Player(int attempts, String name) {
        this.attempts = attempts;
        this.name = name;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getName() {
        return name;
    }
}
