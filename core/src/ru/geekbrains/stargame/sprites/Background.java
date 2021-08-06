package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class Background extends Sprite {

    public Background(Texture texture) {
        super(new TextureRegion(texture));
    }

    // метод для центровки фона


    @Override
    public void resize(Rect worldBounds) {
       setHeightProportion(worldBounds.getHeight());
       pos.set(worldBounds.pos);
    }
}
