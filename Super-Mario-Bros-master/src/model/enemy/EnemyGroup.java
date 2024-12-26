package model.enemy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class EnemyGroup extends Enemy {
    private List<Enemy> enemies = new ArrayList<>();

    public EnemyGroup(double x, double y, BufferedImage style) {
        super(x, y, style);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    @Override
    public void updateLocation() {
        for (Enemy enemy : enemies) {
            enemy.updateLocation();
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }
}
