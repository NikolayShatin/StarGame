package ru.geekbrains.stargame.pool;

import ru.geekbrains.stargame.base.SpritePool;
import ru.geekbrains.stargame.sprites.Bullet;

public class BulletPool extends SpritePool <Bullet> {

    @Override
    protected Bullet newSprite() {
        return new Bullet();
    }
}
