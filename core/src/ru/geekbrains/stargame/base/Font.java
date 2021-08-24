package ru.geekbrains.stargame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Font extends BitmapFont {

    // упростим конструктор, так как штатные слишком навороченные
    public Font(String fontFile, String imageFile) {
        super(Gdx.files.internal(fontFile), Gdx.files.internal(imageFile), false, false);
        getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);// установим фильтр сглаживания для текстуры,
        // работающий при уменьшении и увеличении (сеттер принимает 2 параметра)
    }


    public void setSize(float size) { // принимаем на вход размер шрифта
        getData().setScale(size / getCapHeight());// возвращаем данные для отрисовки,
        // скалируем их на нужную величину (переданная высота на высоту заглавной буквы)
    }


    public GlyphLayout draw(Batch batch, CharSequence str, float x, float y, int halign) {
        return super.draw(batch, str, x, y, 0f, halign, false);
    }
}
