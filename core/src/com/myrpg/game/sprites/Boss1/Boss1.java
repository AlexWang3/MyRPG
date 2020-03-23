package com.myrpg.game.sprites.Boss1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Boss1 {
    private Texture texture;
    private Vector2 position;
    private Rectangle bounds;
    public Boss1(int x,int y){
        this.texture=new Texture("badlogic.jpg");
        this.position=new Vector2(x,y);
        this.bounds=new Rectangle(x,y,texture.getWidth(),texture.getHeight());
    }
    public void update(float dt){
        bounds.setPosition(position.x,position.y);
    }
    public Vector2 getPosition(){
        return position;
    }
    public Texture getTexture(){
        return texture;
    }
    public void dispose(){
        texture.dispose();
    }

    public Rectangle getBounds(){
        return bounds;
    }

}
