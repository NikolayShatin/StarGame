package ru.geekbrains.stargame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.geekbrains.stargame.math.MatrixUtils;
import ru.geekbrains.stargame.math.Rect;

public class BaseScreen implements Screen, InputProcessor {// родительский класс для всех экранов в нашем проекте

    protected SpriteBatch batch; // доступен из наследников

    private Rect screenBounds; // пиксельный вариант прямоугольника
    protected Rect worldBounds; // прямоугольник с мировой системой координат
    private Rect glBounds; // прямоугольник с координатами OpenGL

    private Matrix4 worldToGl; // объявим матрицу проекции
    private Matrix3 screenToWorld; // для пересчета системы событий

    private Vector2 touch;



    @Override
    public void show() { // вызывается самым первым, когда отображается экран
        System.out.println("show"); // залогируем метод
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch(); // инициализация объектов
        screenBounds = new Rect();
        worldBounds = new Rect();
        glBounds = new Rect(0,0,1f,1f);
        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();
        touch = new Vector2();
    }

    @Override
    public void render(float delta) { // срабатывает 60 раз в секунду, поэтому не логируем
        ScreenUtils.clear(0.4f, 0.24f, 0.51f, 1);
    }

    @Override
    public void resize(int width, int height) { // срабатывает после show, в нем задаются размеры окна
        System.out.println("resize width = " + width + " resize heigth = " + height); // залогируем метод
        screenBounds.setSize(width,height); // внутри setSize() они делятся на 2
        screenBounds.setLeft(0); // смещаем точку 0,0 в левый нижний угол
        screenBounds.setBottom(0);

        //теперь можно задать значения для worldBounds, имея ширину и высоту мы можем вычислить aspect

        float aspect = width / (float) height; // получили соотношение сторон
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f*aspect);
        MatrixUtils.calcTransitionMatrix(worldToGl,worldBounds,glBounds);
        MatrixUtils.calcTransitionMatrix(screenToWorld,screenBounds,worldBounds);//пересчет для системы событий
        batch.setProjectionMatrix(worldToGl);
        resize(worldBounds);
    }


    public void resize(Rect worldBounds){
        System.out.println("worldBounds width = " + worldBounds.getWidth() + " worldBounds heigth = " + worldBounds.getHeight());
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
        touch.set(screenX, Gdx.graphics.getHeight() - screenY).mul(screenToWorld);
        touchDown(touch,pointer,button);
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        System.out.println("touchDown touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { // когда отпускае палец
        System.out.println("touchUp screenX = " + screenX + " screenY = " + screenY);
        touch.set(screenX, Gdx.graphics.getHeight() - screenY).mul(screenToWorld);
        touchUp(touch,pointer,button);
        return false;
    }



    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("touchUp touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { // зажали и протащили
        System.out.println("touchDragged screenX = " + screenX + " screenY = " + screenY);
        touch.set(screenX, Gdx.graphics.getHeight() - screenY).mul(screenToWorld);
        touchDragged(touch,pointer);
        return false;
    }


    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println("touchDragged touchX = " + touch.x + " touchY = " + touch.y);
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
