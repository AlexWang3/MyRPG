package com.myrpg.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pain {
    private static final int GRAVITY=-20;
    public static int bottom=0;
    private Vector2 position;
    private Vector2 velocity;
    private static final int BOTTOM=0;
    public PainAnimation painAnimation;
    private Texture texture;
    private Texture reversetexture;
    public boolean RightMove=false;
    public boolean LeftMove=false;
    private boolean inverted=false;
    public boolean shoot=false;
    public boolean jumping=false;
    public float shoottime=0;
    private Rectangle bounds;
    public Pain(int x,int y){
        this.position=new Vector2(x,y);
        this.velocity=new Vector2(0,0);
        texture=new Texture("PainPackage.png");
        reversetexture=new Texture("PainPackageReverse.png");
        painAnimation=new PainAnimation(new TextureRegion(texture),new TextureRegion(reversetexture),10,7,10.0f);
        bounds=new Rectangle(x,y,texture.getWidth(),texture.getHeight());
    }
    public void update(float dt){
        if(position.y>bottom){
            velocity.add(0,GRAVITY);
        }
        else{
            position.y=bottom;
        }
        if(position.y==bottom&&shoot){
            LeftMove=false;
            RightMove=false;
            painAnimation.inverted=inverted;
            if(!painAnimation.inverted) {
                painAnimation.attackUpdate(dt);
            }
            else {
                painAnimation.invertedattackUpdate(dt);
            }
            if(shoottime>0) {
                shoottime-=dt;
                return;
            }
            else{
                shoot=false;
            }
        }
        if(isFlying()&&shoot){
            LeftMove=false;
            RightMove=false;
            painAnimation.inverted=inverted;
            if(!painAnimation.inverted) {
                painAnimation.flyattackUpdate(dt);
            }
            else {
                painAnimation.invertedflyattackUpdate(dt);
            }
            if(shoottime>0) {
                shoottime-=dt;
                return;
            }
            else{
                shoot=false;
            }
        }
        velocity.scl(dt);
        if(RightMove){
            LeftMove=false;
            inverted=false;
            if(!isFlying())
                painAnimation.rightmoveUpdate(dt);
            else {
                painAnimation.inverted=false;
                painAnimation.jump(dt, inverted);
            }
            position.add(200*dt,velocity.y);
        }
        else if(LeftMove){
            RightMove=false;
            inverted=true;
            if(!isFlying())
                painAnimation.leftmovedUpdate(dt);
            else {
                painAnimation.inverted=true;
                painAnimation.jump(dt, inverted);
            }
            position.add(-200*dt,velocity.y);
        }
        else {
            if(jumping) {
                position.add(0, velocity.y);
                jumping=false;
            }
            if(!inverted) {
                painAnimation.inverted=false;
                if(position.y>bottom){
                    painAnimation.jump(dt,inverted);
                    if(!jumping) {
                        position.add(0, velocity.y);
                    }
                }
                else {
                    velocity.y=0;
                    painAnimation.standUpdate(dt);
                }
            }
            else {
                painAnimation.inverted=true;
                if(position.y>bottom){
                    painAnimation.jump(dt,inverted);
                    if(!jumping) {
                        position.add(0, velocity.y);
                    }
                }
                else {
                    velocity.y=0;
                    painAnimation.reverseStandUpdate(dt);
                }
            }
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }
    public void jump(){
        jumping=true;
        velocity.y=350;
    }
    public void handleInput(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            jump();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            shoot=true;
            shoottime=0.1f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)&&(!Gdx.input.isKeyPressed(Input.Keys.A))){
            RightMove=true;
            LeftMove=false;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)&&(!Gdx.input.isKeyPressed(Input.Keys.D))){
            LeftMove=true;
            RightMove=false;
        }
        else  {
            RightMove = false;
            LeftMove = false;
        }
    }
    public Vector2 getPosition() {
        return position;
    }
    public boolean isFlying(){
        return position.y>bottom;
    }
    public TextureRegion getTexture(boolean reverse) {
        return painAnimation.getFrame(reverse);
    }
    public void dispose(){
        texture.dispose();
    }
    public Rectangle getBounds(){
        return bounds;
    }
}
