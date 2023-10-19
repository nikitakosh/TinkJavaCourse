package edu.hw2.task2;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public final Rectangle setWidth(int width) {
        return new Rectangle(width, this.height);
    }

    public final Rectangle setHeight(int height) {
        return new Rectangle(this.width, height);
    }

    public double area() {
        return width * height;
    }
}
