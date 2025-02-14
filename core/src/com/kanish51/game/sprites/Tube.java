package com.kanish51.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH=52;
    protected static final int FLUCTUATION=130;
    protected static final int TUBE_GAP=100;
    protected static final int LOWEST_OPENING=120;
    private Texture topTube,bottomTube;
    private Vector2 posTopTube,posBottomTube;
    private Rectangle boundsTop, boundsBottom;
    private Random rand;

    public Tube(float x)
    {
        topTube=new Texture("toptube.png");
        bottomTube=new Texture("bottomtube.png");
        rand=new Random();
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING + TUBE_GAP);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING + TUBE_GAP);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(x, posTopTube.y);
        boundsBottom.setPosition(x, posBottomTube.y);
    }
    public boolean collides(Rectangle player){
        return player.overlaps(boundsBottom) || player.overlaps(boundsTop);
    }
    public void dispose()
    {
        topTube.dispose();
        bottomTube.dispose();
    }

}
