package model.hero;

import manager.Camera;
import manager.GameEngine;
import model.GameObject;
import view.Animation;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mario extends GameObject {

    private int remainingLives;
    private int coins;
    private int points;
    private double invincibilityTimer;
    private boolean toRight = true;
    private MarioState state;
    private MarioForm marioForm; // Use MarioForm for managing animations and fireball style

    public Mario(double x, double y) {
        super(x, y, null);
        setDimension(48, 48);

        remainingLives = 3;
        points = 0;
        coins = 0;
        invincibilityTimer = 0;

        ImageLoader imageLoader = new ImageLoader();
        // Load frames for all possible Mario forms (Small, Super, Fire)
        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);
        if (leftFrames == null || rightFrames == null) {
            System.out.println("Erreur de chargement des images !");
        } else {
            System.out.println("Images chargées avec succès.");
        }
        Animation animation = new Animation(leftFrames, rightFrames);

        // Initialize Mario with the SmallMario state
        this.marioForm = new MarioForm(animation, false, false);
        this.state = new SmallMarioState(this, marioForm.getAnimation(), false, false, null);
    }

    @Override
    public void draw(Graphics g) {
        boolean movingInX = (getVelX() != 0);
        boolean movingInY = (getVelY() != 0);

        setStyle(marioForm.getCurrentStyle(toRight, movingInX, movingInY));

        super.draw(g);
    }

    public void jump(GameEngine engine) {
        state.jump(engine); // Delegate behavior to the current state
    }

    public void move(boolean toRight, Camera camera) {
        this.toRight = toRight;
        state.move(toRight, camera); // Delegate behavior to the current state
    }

    public void onTouchEnemy(GameEngine engine) {
        state.onTouchEnemy(engine); // Handle enemy collision and state transition
    }

    public Fireball fire() {
        return state.fire(); // Delegate fireball logic to the state
    }

    public void acquireCoin() {
        coins++;
    }

    public void acquirePoints(int points) {
        this.points += points;
    }

    public int getRemainingLives() {
        return remainingLives;
    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    public int getPoints() {
        return points;
    }

    public int getCoins() {
        return coins;
    }



    public boolean getToRight() {
        return toRight;
    }

    public void resetLocation() {
        setVelX(0);
        setVelY(0);
        setX(50);
        setJumping(false);
        setFalling(true);
    }

    public MarioState getState() {
        return state;
    }

    public void setState(MarioState state) {
        this.state = state;
    }

    // Getters and setters for MarioForm (for better access to animation and fireball style)
    public MarioForm getMarioForm() {
        return marioForm;
    }

    public void setMarioForm(MarioForm marioForm) {
        this.marioForm = marioForm;
    }

    public boolean isSuper() {
        return true;
    }
}
