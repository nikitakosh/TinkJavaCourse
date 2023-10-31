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
        CAT(1), DOG(2), BIRD(3), FISH(4), SPIDER(5);

        Type(int priority) {

        }
    }

    enum Sex {
        M(1), F(2);

        Sex(int priority) {

        }
    }
}
