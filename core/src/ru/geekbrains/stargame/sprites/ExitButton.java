package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.base.BaseButton;
import ru.geekbrains.stargame.math.Rect;

public class ExitButton extends BaseButton {

    public static final float PADDING = 0.03f; // отступ для кнопки


    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("btExit")); // кнопка сама определяет с какой текстурой рисоваться
    }


    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.18f);
        setBottom(worldBounds.getBottom() + PADDING);
        setRight(worldBounds.getRight() - PADDING);
    }

    @Override
    public void action() {
        Gdx.app.exit(); // выход из приложения
    }
}
