package com.kanish51.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kanish51.game.FlappyDemo;
import com.kanish51.game.sprites.Bird;
import com.kanish51.game.sprites.Tube;

public class PlayState extends State {

    private static final int TUBE_SPACING = 130;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1,groundPos2;
    private Array<Tube> tubes;
    private Stage stagePlay;
    private Label scoreLabel;
    private float timeState=0f;
    private int score=0;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird=new Bird(30,380);
        bg=new Texture("bg.png");
        ground=new Texture("ground.png");
        scoreLabel=new Label("Score: "+String.valueOf(0),new Label.LabelStyle(new BitmapFont(Gdx.files.internal("yeh.fnt")), Color.WHITE));
        scoreLabel.setFontScale(0.5f,0.5f);
        scoreLabel.setPosition((FlappyDemo.WIDTH)/2f+(scoreLabel.getWidth()/2),(FlappyDemo.HEIGHT)-60);
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
        stagePlay = new Stage(new ScreenViewport());
        groundPos1=new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);;
        groundPos2=new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++)
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH+5)));
        stagePlay.addActor(scoreLabel);
    }

    @Override
    public void handleInput() {

        if(Gdx.input.justTouched())
        {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {

        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x=bird.getPosition().x + 80;
        for(Tube tube : tubes) {
            if (cam.position.x - cam.viewportWidth / 2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING+5) * TUBE_COUNT));
            }
            if(tube.collides(bird.getBounds())){
                gsm.set(new MenuState(gsm,score));
                break;
            }
        }
        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gsm.set(new MenuState(gsm,score));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes){
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
        }
        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        timeState+=Gdx.graphics.getDeltaTime();
        if(timeState>=1f)
        {
            timeState=0f;
            score += 1;
            scoreLabel.setText("Score: "+String.valueOf(score));
        }
        sb.end();
        stagePlay.draw();

    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);

    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
        stagePlay.dispose();
        System.out.println("play disposed");

    }
}
