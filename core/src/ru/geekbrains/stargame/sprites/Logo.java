package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class Logo extends Sprite {

    private Vector2 v;
    private Vector2 touch;
    private static final float V_LEN = 0.01f;


    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        this.v = new Vector2();
        this.touch = new Vector2();
    }


    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.2f);
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }


    @Override
    public void update(float delta) {
        if (touch.dst(pos)>V_LEN){
            pos.add(v);
        }else {pos.set(touch);}
    }
}
