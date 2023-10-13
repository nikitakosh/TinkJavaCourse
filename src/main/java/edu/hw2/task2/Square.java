package edu.hw2.task2;

import edu.hw2.task2.Rectangle;

public class Square extends Rectangle {

    private boolean sideDefined = false;
    @Override
    public void setWidth(int width) {
        if (!sideDefined){
            super.setWidth(width);
            super.setHeight(width);
            sideDefined = true;
        }
    }

    @Override
    public void setHeight(int height) {
        if (!sideDefined){
            super.setWidth(height);
            super.setHeight(height);
            sideDefined = true;
        }
    }
}
