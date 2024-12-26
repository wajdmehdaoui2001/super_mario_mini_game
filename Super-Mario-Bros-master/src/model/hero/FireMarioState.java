package model.hero;

import manager.Camera;
import manager.GameEngine;
import view.Animation;

import java.awt.image.BufferedImage;

public class FireMarioState implements MarioState {
    private Mario mario;
    private Animation animation;
    private boolean isSuper, isFire;
    private BufferedImage fireballStyle;

    public FireMarioState(Mario mario, Animation animation, boolean isSuper, boolean isFire, BufferedImage fireballStyle) {
        this.mario = mario;
        this.animation = animation;
        this.isSuper = isSuper;
        this.isFire = isFire;
        this.fireballStyle = fireballStyle;
    }

    @Override
    public void jump(GameEngine engine) {
        if (!mario.isJumping() && !mario.isFalling()) {
            mario.setJumping(true);
            mario.setVelY(12); // Higher jump for Fire Mario
            engine.playJump();
        }
    }

    @Override
    public void move(boolean toRight, Camera camera) {
        mario.setVelX(toRight ? 5 : -5);  // Déplace Mario à gauche ou à droite
        animation.animate(5, toRight);  // Met à jour l'animation en fonction de la direction
    }


    @Override
    public void onTouchEnemy(GameEngine engine) {
        mario.setState(new SuperMarioState(mario, animation, true, false, fireballStyle));  // Transition to Super Mario
    }

    @Override
    public Fireball fire() {
        return new Fireball(mario.getX(), mario.getY() + 48, fireballStyle, true);  // Fire a fireball
    }

    @Override
    public void acquirePowerUp() {
        // Fire Mario is already at the maximum power level, no need to acquire further power-ups
    }

    @Override
    public void losePower() {
        mario.setState(new SuperMarioState(mario, animation, true, false, fireballStyle));  // Transition to Super Mario
    }

    public Animation getAnimation() {
        return animation;
    }

    public BufferedImage getFireballStyle() {
        return fireballStyle;
    }
    @Override
    public void updateStyle() {

    }
}
