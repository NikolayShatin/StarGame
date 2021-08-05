package ru.geekbrains.stargame.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private static final float V_LEN = 0.9f;
    private Texture img;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 n;
    private Vector2 sub;
    private Vector2 tmp;

    @Override
    public void show() {
        super.show();
        img = new Texture("tiger.jpg");
        pos = new Vector2();
        v = new Vector2();
        //n = new Vector2(0,-0.01f);
        sub = new Vector2();
        tmp = new Vector2();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        tmp.set(sub);
        if (tmp.sub(pos).len() > V_LEN){
            pos.add(v);
        } else {
            pos.set(sub);
        }


    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        sub.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(sub.cpy().sub(pos)).setLength(V_LEN);
        //sub = pos.sub(pos.set(screenX, Gdx.graphics.getHeight() - screenY));
        //v.set(2,1);
        //n.set(0,-0.01f);
        return false;
    }

//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        pos.set(screenX, Gdx.graphics.getHeight() - screenY);
//
//        return super.touchDragged(screenX, screenY, pointer);
//    }
}
