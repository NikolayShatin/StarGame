package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class Bullet extends Sprite {

    private Rect worldBounds; // границы игрового мира
    private Vector2 v;
    private  int damage; // урон,который наносит пуля
    private Sprite owner; // владелец пули

    public Bullet() {
        regions = new TextureRegion[1]; // проинициализируем массив регионов, но текстуру пули
        // мы не знаем (есть наша, есть вражеская). Текстуру будем присваивать
        // когда будем лдоставать пулю из пула объектов

        v = new Vector2();
    }

    public void set(
            Sprite owner, // владелец пули
            TextureRegion region, // ее текстура
            Vector2 pos0, // начальное положение пули
            Vector2 v0, // начальная скорость пули
            float height,// размер пули (чем больше корабль, тем больше размер)
            Rect worldBounds, // актуальные границы игрового мира, так как пуля создается не в момент
            //инициализации всех объектов, а после метода resize, оэтому мы не можем в пулю передавать
            // метод resize, потому что его в этот момент еще не существует
            int damage // уронб наносимый пулей
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    @Override// для того, чтобы пуля могла лететь
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {//условие при котором пуля будет попадать в список свободных объектов
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getOwner() {
        return owner;
    }
}
