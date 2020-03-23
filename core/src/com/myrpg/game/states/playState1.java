package com.myrpg.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.myrpg.game.MyRPG;
import com.myrpg.game.sprites.Boss1.Boss1;
import com.myrpg.game.sprites.Health;
import com.myrpg.game.sprites.Pain;
import com.myrpg.game.sprites.Shoot;

public class playState1 extends state  {
    private Texture Scene1;
    private Pain pain;
    private Health health;
    private Array<Shoot> shoots;
    private Array<Shoot> removeShoots;
    private Boss1 boss;
    public playState1(GameStateManager gsm) {
        super(gsm);
        Scene1=new Texture("Scene1.png");
        cam.setToOrtho(false, MyRPG.WIDTH,MyRPG.HEIGHT);
        pain=new Pain(0,150);
        shoots=new Array<Shoot>();
        removeShoots=new Array<Shoot>();
        this.health=new Health(100);
        this.boss=new Boss1(200,100);
    }

    @Override
    protected void handleInput() {
        pain.handleInput();
        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            shoots.add(new Shoot(pain.getPosition().x,pain.getPosition().y,pain.painAnimation.inverted));
        }
        if(pain.getPosition().x>800){
            gsm.set(new playState2(gsm));
            dispose();
        }


    }

    @Override
    public void update(float dt) {
        handleInput();
        pain.update(dt);
        boss.update(dt);
        for(int i=0;i<shoots.size;i++){
            shoots.get(i).update(dt);
            if(shoots.get(i).getPosition().x>700||shoots.get(i).getPosition().x<10)
                removeShoots.add(shoots.get(i));
        }
        for(int i=0;i<shoots.size;i++){
            if(shoots.get(i).collide(boss.getBounds())){
                health.currentHealth-=5;
                removeShoots.add(shoots.get(i));
            }
        }
        shoots.removeAll(removeShoots,true);
        health.update();
        cam.position.x=pain.getPosition().x+40;
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(Scene1,0,0);
        sb.draw(pain.getTexture(pain.painAnimation.inverted),pain.getPosition().x,pain.getPosition().y);
        sb.draw(health.textureRegion,65,440);
        for(Shoot s:shoots){
            sb.draw(s.getTexture(),s.getPosition().x,s.getPosition().y,s.getTexture().getRegionWidth()/10,s.getTexture().getRegionHeight()/10);
        }
        sb.draw(boss.getTexture(),boss.getPosition().x,boss.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        Scene1.dispose();
    }
}
