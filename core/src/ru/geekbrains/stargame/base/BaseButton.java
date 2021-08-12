package ru.geekbrains.stargame.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseButton extends Sprite {

    private static final float PRESS_SCALE = 0.9f; // обратная связь. При нажатии кнопка уменьшается на 10%

    private int pointer;//номер пальца, которым мы нажимаем кнопку
    private boolean pressed;// состояние кнопки нажата/нет


    public BaseButton(TextureRegion region) {
        super(region);
    }

    public abstract void action();

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (pressed || !isMe(touch)) {//если кнопка нажата или не попали по кнопке то метод не выполняется
            return false;
        }
        this.pointer = pointer; // сохраняем номер пальца, которым нажали на кнопку
        scale = PRESS_SCALE; // уменьшаем кнопку
        pressed = true;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (this.pointer != pointer || !pressed) {//проверяем, что отпустили тот палец, которым изначально нажимали кнопку или кнопка не нажата
            return false;
        }

        if (isMe(touch)) {// когда пользователь нажимает кнопку, а потом передумывает
            action();
        }
        pressed = false; // возвращаем кнопку в исходное состояние
        scale = 1f; // возвращаем кнопку в исходное состояние

        return false;
    }
}
