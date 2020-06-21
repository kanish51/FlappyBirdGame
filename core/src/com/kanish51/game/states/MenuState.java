package com.kanish51.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kanish51.game.FlappyDemo;

public class MenuState extends State {
    private Texture background;
    private Stage stage;
    private Texture welcome;
    private ImageButton button;
    private Label label;
    public MenuState(GameStateManager gsm,int score)
    {
        super(gsm);
        background=new Texture("bg.png");
        welcome=new Texture("welcome.png");
        label=new Label("High Score: "+String.valueOf(score),new Label.LabelStyle(new BitmapFont(Gdx.files.internal("yeh.fnt")), Color.WHITE));
        label.setFontScale(0.5f,0.5f);
        label.setPosition((FlappyDemo.WIDTH/2f)-(label.getWidth()/4),(FlappyDemo.HEIGHT/4f));
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
        button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playbtn.png")))));
        stage = new Stage(new ScreenViewport());
        button.setPosition((FlappyDemo.WIDTH/2f)-(button.getWidth()/2),FlappyDemo.HEIGHT/2);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleInput();
            };
        });
        stage.addActor(button);
        stage.addActor(label);
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void handleInput() {
        gsm.set(new PlayState(gsm));
    }

    @Override
    public void update(float dt) {
        //handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(welcome,(FlappyDemo.WIDTH/4)-(welcome.getWidth()/2),(FlappyDemo.HEIGHT/4f)+button.getHeight()+5);
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        welcome.dispose();
        stage.dispose();
        System.out.println("menu disposed");
    }
}
