package ru.geekbrains.stargame.pool;

import ru.geekbrains.stargame.base.SpritePool;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprites.EnemyShip;

public class EnemyPool extends SpritePool<EnemyShip> {

    private final Rect worldBounds;
    private final BulletPool bulletPool;

    public EnemyPool(Rect worldBounds, BulletPool bulletPool) {
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
    }

    @Override
    protected EnemyShip newSprite() {
        return new EnemyShip(worldBounds, bulletPool);
    }
}
