package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Ship;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPull;

public class MainShip extends Ship {

    private static final float HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;
//    private final Vector2 v0 = new Vector2(0.5f, 0);
//    private final Vector2 v = new Vector2();

    private boolean pressedLeft;
    private boolean pressedRight;

    private static final int INVALID_POINTER = -1;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;


    private static final float RELOAD_INTERVAL = 0.2f;

    private Sound bulletSound;


    public MainShip(TextureAtlas atlas, BulletPull bulletPool, Sound bulletSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.bulletSound = bulletSound;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV = new Vector2(0, 0.5f);
        bulletHeight = 0.01f;
        bulletDamage = 1;
        reloadInterval = RELOAD_INTERVAL;
        bulletPosition = new Vector2();
        v0.set(0.5f, 0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer >= RELOAD_INTERVAL) {
            reloadTimer = 0f;
            shoot();
        }


//        if (getRight() > worldBounds.getRight()){// если корабль высунулся за правый край экрана
//            setRight(worldBounds.getRight());//выравниваем его
//            stop(); // и останавливаем
//        }
//        if (getLeft()< worldBounds.getLeft()){
//            setLeft(worldBounds.getLeft());
//            stop();
//        }

        if (getLeft() > worldBounds.getRight()) {// проверим, что корабль полностью ушел за экран
            setRight(worldBounds.getLeft());
        }

        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
        this.worldBounds = worldBounds;
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }

        return false;
    }


    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
//            case Input.Keys.UP:
//                shoot();
//                break;
        }

        return false;
    }


    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotateDeg(180);
    }

    private void stop() {
        v.setZero();
    }

    private void shoot() { // метод стрельбы
        Bullet bullet = bulletPool.obtain();
        bulletPosition.set(pos.x, pos.y + getHalfHeight());
        bullet.set(this, bulletRegion, bulletPosition, bulletV, bulletHeight, worldBounds, bulletDamage);
        bulletSound.play();
    }


}
