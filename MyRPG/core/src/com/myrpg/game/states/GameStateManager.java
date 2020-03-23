package com.myrpg.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<state>states;
    public GameStateManager(){
        states=new Stack<state>();
    }
    public void push(state state){
        this.states.push(state);
    }
    public void pop(){
        this.states.pop();
    }
    public void set(state state){
        this.states.pop();
        this.states.push(state);
    }
    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
