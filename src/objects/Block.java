package objects;

import abstractshapes.Point;
import abstractshapes.Rectangle;
import biuoop.DrawSurface;
import game.GameLevel;
import game.GameObject;
import hitlisteners.HitListener;
import hitlisteners.HitNotifier;
import utils.Utils;
import utils.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * An obstacle block in the game.
 */
public class Block implements Collidable, Sprite, GameObject, HitNotifier {

    private abstractshapes.Rectangle blockShape;
    private Color blockColor;
    private List<HitListener> hitListeners;

    /**
     * Creates a block with a given rectangular shape.
     *
     * @param blockShape block's shape
     * @param blockColor block's color
     */
    public Block(abstractshapes.Rectangle blockShape, Color blockColor) {
        this.blockShape = blockShape;
        this.blockColor = blockColor;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Creates a block with a given rectangular shape.
     *
     * @param x      block's upper left point's x coordinate
     * @param y      block's upper left point's y coordinate
     * @param height block's height
     * @param width  block's width
     * @param color block's color
     */
    public Block(double x, double y, double height, double width, Color color) {
        this(new abstractshapes.Rectangle(new abstractshapes.Point(x, y), height, width), color);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return blockShape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        notifyHit(hitter);

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (Utils.approximatelyEqual(collisionPoint.getX(), blockShape.getUpperLeft().getX())
                || Utils.approximatelyEqual(collisionPoint.getX(), blockShape.getUpperRight().getX())) {
            dx *= -1;
        }

        if (Utils.approximatelyEqual(collisionPoint.getY(), blockShape.getUpperLeft().getY())
                || Utils.approximatelyEqual(collisionPoint.getY(), blockShape.getLowerLeft().getY())) {
            dy *= -1;
        }

        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        blockShape.drawOn(surface, blockColor);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Removes the object from the game.
     *
     * @param gameLevel game session
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
        removeAllHitListeners();
    }

    @Override
    public void addHitListener(HitListener listener) {
        hitListeners.add(listener);
    }

    @Override
    public void removeHitListener(HitListener listener) {
        hitListeners.remove(listener);
    }

    private void removeAllHitListeners() {
        hitListeners.clear();
    }

    private void notifyHit(Ball hitter) {
        List<HitListener> listenersCopy = new ArrayList<>(hitListeners);

        for (HitListener listener : listenersCopy) {
            listener.hitEvent(this, hitter);
        }

        hitListeners.clear();
        hitListeners.addAll(listenersCopy);
    }
}
