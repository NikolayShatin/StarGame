package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;


import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class GameOver extends Sprite {

    public static final float HEIGHT = 0.08f;

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
    }

    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }
}
