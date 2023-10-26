package edu.hw4;

public record Animal(
        String name,
        Type type,
        Sex sex,
        int age,
        int height,
        int weight,
        Boolean bites
) {
    public int paws() {
        final int CAT_AND_DOG_PAWS = 4;
        final int BIRD_PAWS = 2;
        final int SPIDER_PAWS = 8;
        return switch (type) {
            case CAT, DOG -> CAT_AND_DOG_PAWS;
            case BIRD -> BIRD_PAWS;
            case FISH -> 0;
            case SPIDER -> SPIDER_PAWS;
        };
    }

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }
}
