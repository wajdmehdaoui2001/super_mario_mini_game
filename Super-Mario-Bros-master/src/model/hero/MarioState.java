package model.hero;

import manager.Camera;
import manager.GameEngine;

public interface MarioState {
    void jump(GameEngine engine);

    void onTouchEnemy(GameEngine engine);
    Fireball fire();
    void acquirePowerUp();
    void losePower();
    void move(boolean toRight, Camera camera);
    void updateStyle();

}
