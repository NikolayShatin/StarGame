package ru.geekbrains.stargame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class BaseScreen implements Screen, InputProcessor {// родительский класс для всех экранов в нашем проекте

    protected SpriteBatch batch; // доступен из наследников

    @Override
    public void show() { // вызывается самым первым, когда отображается экран
        System.out.println("show"); // залогируем метод
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch(); // инициализация объектов
    }

    @Override
    public void render(float delta) { // срабатывает 60 раз в секунду, поэтому не логируем
        ScreenUtils.clear(0.4f, 0.24f, 0.51f, 1);
    }

    @Override
    public void resize(int width, int height) { // срабатывает после show, в нем задаются размеры окна
        System.out.println("resize width = " + width + "resize heigth = " + height); // залогируем метод
    }

    @Override
    public void pause() { // срабатывает, когда мы сворачиваем игру
        System.out.println("pause");
    }

    @Override
    public void resume() { // срабатывает, когда мы разворачиваем игру
        System.out.println("resume");
    }

    @Override
    public void hide() { // срабатывает, когда мы закрываем экран
        System.out.println("hide"); // залогируем метод
        dispose();
    }

    @Override
    public void dispose() { // вызывается из hide(), очищает ненужные объекты из памяти
        System.out.println("dispose"); // залогируем метод
        batch.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) { //
        System.out.println("keyTyped character = " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { // когда тапаем по тачскрину или нажимаем мышкой
        System.out.println("touchDown screenX = " + screenX + " screenY = " + screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { // когда отпускае палец
        System.out.println("touchUp screenX = " + screenX + " screenY = " + screenY);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { // зажали и протащили
        System.out.println("touchDragged screenX = " + screenX + " screenY = " + screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {


        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        System.out.println("scrolled amountX = " + amountX + " amountY = " + amountY);
        return false;
    }
}
