package com.myrpg.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Health {
    private Texture texture;
    public float currentHealth;
    public TextureRegion textureRegion;
    private float maxHealth;
    public Health(float maxHealth){
        this.texture=new Texture("health.png");
        this.textureRegion=new TextureRegion(texture,0,0,texture.getWidth()/2,texture.getHeight()/5);
        this.maxHealth=maxHealth;
        this.currentHealth=maxHealth;
    }
    public void update(){
        this.textureRegion=new TextureRegion(texture,(int)((texture.getWidth()/2)*((maxHealth-currentHealth)/maxHealth)),0,texture.getWidth()/2,texture.getHeight()/5);
    }



}
