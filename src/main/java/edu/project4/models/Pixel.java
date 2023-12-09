package edu.project4.models;

public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;

    public Pixel(int r, int g, int b, int hitCount, double normal) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
        this.normal = normal;
    }

    public void saturateRGB(int r, int g, int b) {
        this.r = (this.r + r) / 2;
        this.g = (this.g + g) / 2;
        this.b = (this.b + b) / 2;
    }

    public void setColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int getHitCount() {
        return hitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public void increaseHitCount() {
        hitCount++;
    }

    public void saturate(Color color) {
        if (hitCount == 0) {
            increaseHitCount();
            setColor(
                    color.r(),
                    color.g(),
                    color.b()
            );
        } else {
            increaseHitCount();
            saturateRGB(
                    color.r(),
                    color.g(),
                    color.b()
            );
        }
    }

}
