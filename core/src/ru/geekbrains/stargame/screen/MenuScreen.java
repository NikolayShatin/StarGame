package ru.geekbrains.stargame.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprites.Background;
import ru.geekbrains.stargame.sprites.ExitButton;
import ru.geekbrains.stargame.sprites.PlayButton;
import ru.geekbrains.stargame.sprites.Star;


public class MenuScreen extends BaseScreen {


    private static final int STAR_COUNT = 256; //зададим количество звезд
    private static final float V_LEN = 0.05f; // зададим длину вектора скорости константой
    private Texture bg;
    private Background background;

    private final Game game; // для доступа к методу переключения экранов

    private TextureAtlas atlas;

    private Star[] stars; // создадим массив звезд
    private ExitButton exitButton; // создадим кнопку выхода
    private PlayButton playButton;

    public MenuScreen(Game game) { // конструктор с переменной Game
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        atlas = new TextureAtlas("textures/menuAtlas.tpack"); // передаем ссылку на pack-файл
        stars = new Star[STAR_COUNT]; // проинициализируем массив звезд
        for (int i = 0; i < stars.length; i++) { // проинициализируем звезды
            stars[i] = new Star(atlas);
        }

        exitButton = new ExitButton(atlas);
        playButton =new PlayButton(atlas, game);
    }


    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        exitButton.resize(worldBounds);
        playButton.resize(worldBounds);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        exitButton.touchDown(touch, pointer, button);
        playButton.touchDown(touch, pointer, button);
        return false;
    }


    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        exitButton.touchUp(touch, pointer, button);
        playButton.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        exitButton.draw(batch);
        playButton.draw(batch);
        batch.end();
    }


}
