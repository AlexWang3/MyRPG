package com.myrpg.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PainAnimation {
    private Array<TextureRegion> frames;
    private Array<TextureRegion> reverseframes;
    private float currentFrameTime;
    private float maxFrameTime;
    private float cycleTime;
    private int frameCount;
    public int frame;
    public boolean inverted=false;
    public PainAnimation(TextureRegion region,TextureRegion reverseRegion,int frameCountWidth,int frameCountHeight, float cycleTime){
        frames=new Array<TextureRegion>();
        int frameWidth=50;
        int frameHeight=50;
        for (int i=0;i<350;i+=50){
            for(int j=0;j<500;j+=50) {
                frames.add(new TextureRegion(region, j, i, frameWidth,frameHeight));
            }
        }
        reverseframes=new Array<TextureRegion>();
        for (int i=0;i<350;i+=50){
            for(int j=0;j<500;j+=50) {
                reverseframes.add(new TextureRegion(reverseRegion, j, i, frameWidth,frameHeight));
            }
        }
        this.frameCount=frameCountHeight*frameCountWidth;
        this.cycleTime=cycleTime;
        maxFrameTime =cycleTime/frameCount;
    }
    public void standUpdate(float dt){
        setFrameNumber(0,4,0.785f,dt);
    }
    public void reverseStandUpdate(float dt){
        setFrameNumber(9,5,0.785f,dt);
    }
    public void rightmoveUpdate(float dt){
        setFrameNumber(20,23,0.785f,dt);
    }
    public void leftmovedUpdate(float dt){
        setFrameNumber(29,26,0.785f,dt);
    }
    public void jump(float dt,boolean inverted){
        if(inverted)
            frame=50;
        else
            frame=59;
    }
    public void setFrameNumber(int start,int end,float cycletime,float dt){
        inverted=start>end;
        int small=Math.min(start,end);
        int big=Math.max(start,end);
        if(!(frame>=small&&frame<=big))
            frame=start;
        currentFrameTime+=dt;
        this.cycleTime=cycletime;
        maxFrameTime=cycleTime/4;
        if(currentFrameTime>maxFrameTime){
            if(inverted)
                frame--;
            else
                frame++;
            currentFrameTime= 0;
        }
        if (frame<=small&&inverted)
            frame=big;
        if(frame>=big&&(!inverted))
            frame=small;
    }
    public void attackUpdate(float dt){
        inverted=false;
        if(!(frame>=30&&frame<=34))
            frame=30;
        currentFrameTime+=dt;
        this.cycleTime=0.1f;
        maxFrameTime=cycleTime/4;
        if(currentFrameTime>maxFrameTime){
            frame++;
            currentFrameTime= 0;
        }
    }
    public void invertedattackUpdate(float dt){
        inverted=true;
        if(!(frame>=35&&frame<=39))
            frame=39;
        currentFrameTime+=dt;
        this.cycleTime=0.1f;
        maxFrameTime=cycleTime/4;
        if(currentFrameTime>maxFrameTime){
            frame--;
            currentFrameTime= 0;
        }
    }
    public void flyattackUpdate(float dt){
        inverted=false;
        if(!(frame>=12&&frame<=16))
            frame=12;
        currentFrameTime+=dt;
        this.cycleTime=0.1f;
        maxFrameTime=cycleTime/4;
        if(currentFrameTime>maxFrameTime){
            frame++;
            currentFrameTime= 0;
        }
    }
    public void invertedflyattackUpdate(float dt){
        inverted=true;
        if(!(frame>=13&&frame<=17))
            frame=17;
        currentFrameTime+=dt;
        this.cycleTime=0.1f;
        maxFrameTime=cycleTime/4;
        if(currentFrameTime>maxFrameTime){
            frame--;
            currentFrameTime= 0;
        }
    }

    public TextureRegion getFrame(boolean reverse){
        if(!reverse)
            return frames.get(frame);
        else
            return reverseframes.get(frame);
    }
}
