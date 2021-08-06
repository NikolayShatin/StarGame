package ru.geekbrains.stargame.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.math.Rect;

public class Sprite extends Rect {


    protected float angle; // угол поворота
    protected float scale = 1f; //отвечает за скалирование объекта, по умолчанию 1f, т.е натуральная величина вектора
    protected TextureRegion[] regions; // для анимации состоящей из нескольких текстур
    protected int frame;// указывает на текущий элемент в TextureRegion


    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1]; // иициализируем единичный массив
        regions[0] = region; // в который положим TextureRegion
    }


    //сейчас в метод drow мы передавали много параметров и настраивали все в ручную.
    // Мы же хотим в классе MenuScreen просто передавать события, а логика отрисовки пусть
    // будет в спрайте. Поэтому в draw передаем только батчер.

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame], // первое, что мы рисуем, это текущий фрейм нашего региона
                getLeft(), getBottom(),// выбираем точку отрисовки, смещая ее на половину ширины и на половину высоты,
                //т.е. теперь отрисовываем визуально из центра
                halfWidth, halfHeight,// точку поворота установим в половину ширины, половину высоты
                getWidth(), getHeight(),// ширина и высота
                scale, scale,// скалирование по оси X и Y
                angle // угол поворота
        );
    }

    //дополнительные методы спарайта, для передачи различных событий

public void resize (Rect worldBounds){

}

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer, int button) {
        return false;
    }


    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }


    //вспомогательный метод, который умеет расчитывать ширину картики в зависимости от высоты, т.е.
    // мы спрайту будем задавать только высоту, а ширина будет считаться автоматически


    public void setHeightProportion(float height){
        setHeight(height);
        float aspect = regions[frame].getRegionWidth()/(float)regions[frame].getRegionHeight();
        setWidth(height*aspect);
    }

    public void update(float delta) {

    }

}
