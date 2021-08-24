package ru.geekbrains.stargame.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritePool<T extends Sprite> { // принимает только наследников Sprite

    protected final List<T> activeSprites = new ArrayList<>(); // динамический массив активных объектов
    protected final List<T> freeSprites = new ArrayList<>(); // динамический массив свободных объектов

    protected abstract T newSprite(); // метод инициализации объекта

    public T obtain() { // метод реализующий основную логику спрайта
        T sprite;
        if (freeSprites.isEmpty()) { // если массив спрайьтов пуст
            sprite = newSprite();
        } else {
            sprite = freeSprites.remove(freeSprites.size() - 1); // достаем существующий спрайт
        }
        activeSprites.add(sprite); // помещаем этот спрайт в массив активных объектов
        System.out.println(getClass().getName()+" active/free : " + activeSprites.size() + "/" + freeSprites.size());
        return sprite;
    }

    public void updateActiveSprites(float delta) { // для передачи объектам событий
        for (Sprite sprite : activeSprites) {
            if (!sprite.isDestroyed()) {
                sprite.update(delta);
            }
        }
    }

    public void drawActiveSprites(SpriteBatch batch) { // для отрисовки всех объектов
        for (Sprite sprite : activeSprites) {
            if (!sprite.isDestroyed()) {
                sprite.draw(batch);
            }
        }
    }

    private void free(T sprite) { // метод удаления
        if (activeSprites.remove(sprite)) {// если успешно удалили объект из списка активных объектов
            freeSprites.add(sprite);
            System.out.println(getClass().getName()+" active/free : " + activeSprites.size() + "/" + freeSprites.size());
        }
    }

    public void freeAllDestroyedActiveSprites() { // более сложный метод удалдения,
        // освобождающий все задестроенные активные объекты
        for (int i = 0; i < activeSprites.size(); i++) {
            T sprite = activeSprites.get(i);
            if (sprite.isDestroyed()) {
                free(sprite);
                i--;
                sprite.flushDestroy();
            }
        }
    }

    public void dispose() { // очистка объектов
        activeSprites.clear();
        freeSprites.clear();
    }


    public List<T> getActiveSprites() {
        return activeSprites;
    }
}
