package model.hero;

import manager.Camera;
import manager.GameEngine;
import manager.GameStatus;
import view.Animation;

import java.awt.image.BufferedImage;

public class SmallMarioState implements MarioState {
    private Mario mario;
    private Animation animation;
    private boolean isSuper, isFire;
    private BufferedImage fireballStyle;

    public SmallMarioState(Mario mario, Animation animation, boolean isSuper, boolean isFire, BufferedImage fireballStyle) {
        this.mario = mario;
        this.animation = animation;
        this.isSuper = isSuper;
        this.isFire = isFire;
        this.fireballStyle = fireballStyle;
    }

    @Override
    public void jump(GameEngine engine) {
        mario.setVelY(-10);
        mario.setJumping(true);
    }

    @Override
    public void move(boolean toRight, Camera camera) {
        mario.setVelX(toRight ? 5 : -5);  // Déplace Mario à gauche ou à droite
        animation.animate(5, toRight);  // Met à jour l'animation en fonction de la direction
    }

    @Override
    public void onTouchEnemy(GameEngine engine) {
        mario.setRemainingLives(mario.getRemainingLives() - 1);
        if (mario.getRemainingLives() <= 0) {
            engine.setGameStatus(GameStatus.GAME_OVER);  // Fin du jeu si les vies sont épuisées
        }
    }

    @Override
    public Fireball fire() {
        return null;  // Small Mario ne peut pas tirer
    }

    @Override
    public void acquirePowerUp() {
        mario.setState(new SuperMarioState(mario, animation, true, false, fireballStyle));  // Transition vers Super Mario
    }

    @Override
    public void losePower() {

    }

    @Override
    public void updateStyle() {

    }
}
