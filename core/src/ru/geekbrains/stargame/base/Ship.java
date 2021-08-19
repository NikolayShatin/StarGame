package ru.geekbrains.stargame.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPull;

public class Ship extends Sprite {

    protected final Vector2 v0;
    protected final Vector2 v;

    protected Rect worldBounds;
    protected BulletPull bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV; //вектор скорости пули
    protected float bulletHeight; // размер пули
    protected int bulletDamage;
    protected Vector2 bulletPosition; // чтобы вылетало из носа коробля


    protected float reloadInterval;
    protected float reloadTimer;


    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        v = new Vector2();
        v0= new Vector2();
    }
}
