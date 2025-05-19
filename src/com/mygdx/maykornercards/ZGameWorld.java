package com.mygdx.maykornercards;

import com.badlogic.gdx.math.Rectangle;

public class ZGameWorld {

    private Rectangle rect = new Rectangle(0, 0, 17, 12);

    public ZGameWorld(int midPointY) {
    }

    public void update(float delta) {
        rect.x++;
        if (rect.x > 137) {
            rect.x = 0;
        }
    }
    public Rectangle getRect() {
        return rect;
    }

}
