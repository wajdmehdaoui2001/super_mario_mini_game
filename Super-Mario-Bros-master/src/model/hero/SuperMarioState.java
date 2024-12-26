package model.hero;

import manager.Camera;
import manager.GameEngine;
import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class SuperMarioState implements MarioState {
    private Mario mario;
    private Animation animation;
    private boolean isSuper, isFire;
    private BufferedImage fireballStyle;

    public SuperMarioState(Mario mario, Animation animation, boolean isSuper, boolean isFire, BufferedImage fireballStyle) {
        this.mario = mario;
        this.animation = animation;
        this.isSuper = isSuper;
        this.isFire = isFire;
        this.fireballStyle = fireballStyle;
    }

    @Override
    public void jump(GameEngine engine) {
        mario.setVelY(-12);  // Mario jumps higher in Super Mario state
        mario.setJumping(true);
    }

    @Override
    public void move(boolean toRight, Camera camera) {
        mario.setVelX(toRight ? 5 : -5);  // Déplace Mario à gauche ou à droite
        animation.animate(5, toRight);  // Met à jour l'animation en fonction de la direction
    }

    @Override
    public void onTouchEnemy(GameEngine engine) {
        mario.setState(new SmallMarioState(mario, animation, false, false, fireballStyle));  // Transition vers Small Mario
    }

    @Override
    public Fireball fire() {
        if (isFire) {
            // Crée un Fireball si Mario est dans l'état Fire
            return new Fireball(mario.getX(), mario.getY(), fireballStyle, true);
        }
        return null;
    }

    @Override
    public void acquirePowerUp() {
        mario.setState(new FireMarioState(mario,animation,false,true,fireballStyle));  // Transition vers Fire Mario
    }

    @Override
    public void losePower() {
        mario.setState(new SmallMarioState(mario, animation, false, false, fireballStyle));  // Transition vers Small Mario
    }


    @Override
    public void updateStyle() {

    }
}
