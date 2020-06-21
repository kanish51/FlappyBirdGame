package com.kanish51.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.kanish51.game.FlappyDemo;

public class MenuState extends State {
    private Texture background;
    private Texture playbtn;
    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        background=new Texture("bg.png");
        playbtn=new Texture("playbtn.png");
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
    }
    @Override
    public void handleInput() {
        
        if(Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(playbtn,(cam.position.x)-(playbtn.getWidth()/2),cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();
        System.out.println("menu disposed");
    }
}
