package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.math.Rnd;

public class Star extends Sprite {

    private Vector2 v;
    private Rect worldBounds;    // так как звезда будет лететь по экрану, нам надо узнавать границы игрового мира, чтобы возвращать
    //ее с противоположной стороны, чтобы не создавать каждый раз новую звезду


    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        v = new Vector2();
    }


    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds; // сохраняем границы игрового мира
        float height = Rnd.nextFloat(0.005f, 0.013f);//выставим размер звезды случайным, для этого используем класс Rnd
        setHeightProportion(height);
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());// спозиционируем звезду на экране от левого до правого края случайным образом
        float y = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());// спозиционируем звезду на экране от верха до низа случайным образом
        pos.set(x, y); //задаем положение звезды
        v.set(Rnd.nextFloat(-0.005f, 0.005f), getHeight() * -7);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta); // сложение векторов с умножением на скаляр, синхронизируем через delta с частотой срабатывания метода update
       checkAndHandleBounds();
    }


    private void checkAndHandleBounds() { // проверка, что звезда ушла за пределы поля в процесе движения
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());// выставляем звезду по правому краю
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        if (getBottom() > worldBounds.getTop()){
            setTop(worldBounds.getBottom());
        }
    }


}
