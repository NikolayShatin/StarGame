package ru.geekbrains.stargame.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprites.Background;

public class MenuScreen extends BaseScreen {

    private static final float V_LEN = 0.05f; // зададим длину вектора скорости константой
    private Texture bg;
    private Vector2 pos;
    private Background background;


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        pos = new Vector2();
    }


    @Override
    public void resize(Rect worldBounds) {
       background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch); // background уже сам знает как себя нарисовать
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }
}
