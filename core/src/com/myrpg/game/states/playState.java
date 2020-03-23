package com.myrpg.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.myrpg.game.MyRPG;
import com.myrpg.game.sprites.Pain;
import com.myrpg.game.sprites.Shoot;

public class playState extends state  {
    private Texture Scene1;
    private Pain pain;
    private Array<Shoot> shoots;
    public playState(GameStateManager gsm) {
        super(gsm);
        Scene1=new Texture("Scene1.png");
        cam.setToOrtho(false, MyRPG.WIDTH,MyRPG.HEIGHT);
        pain=new Pain(0,150);
        shoots=new Array<Shoot>();

    }

    @Override
    protected void handleInput() {
        pain.handleInput();
        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            shoots.add(new Shoot(pain.getPosition().x,pain.getPosition().y,pain.painAnimation.inverted));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        pain.update(dt);
        for(int i=0;i<shoots.size;i++){
            shoots.get(i).update(dt);
            if(shoots.get(i).getPosition().x>900||shoots.get(i).getPosition().x<-100)
                shoots.removeIndex(i);
        }
        cam.position.x=pain.getPosition().x+40;
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(Scene1,0,0);
        sb.draw(pain.getTexture(pain.painAnimation.inverted),pain.getPosition().x,pain.getPosition().y);
        for(Shoot s:shoots){
            sb.draw(s.getTexture(),s.getPosition().x,s.getPosition().y,s.getTexture().getRegionWidth()/10,s.getTexture().getRegionHeight()/10);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        Scene1.dispose();
    }
}
