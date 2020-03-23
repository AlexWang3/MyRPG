package com.myrpg.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Shoot {
    private Vector2 position;
    private Texture texture;
    private ShootAnimation shootAnimation;
    private int velocity=10;
    private boolean reverse;
    private Rectangle bounds;
    public Shoot(float x,float y,boolean reverse){
        this.position=new Vector2(x,y);
        texture=new Texture("shoot.png");
        shootAnimation=new ShootAnimation(new TextureRegion(texture),2,0.1f);
        this.reverse=reverse;
        this.bounds=new Rectangle(x,y,texture.getWidth()/20,texture.getHeight()/10);
    }
    public void update(float dt){
        shootAnimation.update(dt);
        if(reverse){
            position.add(-velocity,0);
        }
        else{
            position.add(velocity,0);
        }
        this.bounds.setPosition(position.x,position.y);
    }
    public TextureRegion getTexture(){
        return shootAnimation.getFrame();
    }
    public Vector2 getPosition(){
        return position;
    }
    public void dispose(){
        texture.dispose();
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public boolean collide(Rectangle boss){
        return boss.overlaps(bounds);
    }
}
