package levels;

import basicobjects.Point;
import basicobjects.Velocity;
import biuoop.DrawSurface;
import gameobjects.Block;
import gameobjects.Rectangle;
import listeners.DirectHitListener;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 06.06.2021
 * level 2.
 */
public class One implements LevelInformation {
    public static final int DX = 0;
    public static final int DY = 5;
    private int blocks;
    private boolean didIt;

    /**
     * constructor.
     */
    public One() {
        this.didIt = false;
    }
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(i, new Velocity(DX, DY + i));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }


    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public void setDidIt(boolean value) {
        this.didIt = value;
    }
    @Override
    public boolean getDidIt() {
        return this.didIt;
    }

    @Override
    public Sprite getBackground() {
        gameobjects.Rectangle rect = new Rectangle(new Point(0, 0), 800, 600);
        rect.setColor(Color.BLACK);
        return new Block(rect);
    }

    @Override
    public List<Block> createBlocks(List<Block> blocksList, int x, int y, int width,
                                    int height, Color color, int size) {
        return null;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int x = 350;
        int y = 185;
        int width = 40;
        int height = 40;
        Rectangle rectangle = new Rectangle(new Point(x, y), width, height);
        rectangle.setColor(Color.RED);
        Block block = new Block(rectangle);
        block.addHitListener(new DirectHitListener(this));
        blockList.add(block);
        this.blocks = blockList.size();
        return blockList;
    }

    @Override
    public List<Color> createColors() {
        return null;
    }

    @Override
    public boolean isDirectHit() {
        return true;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.drawCircle(370, 200, 50);
        d.setColor(Color.MAGENTA);
        d.drawCircle(370, 200, 100);
        d.setColor(Color.CYAN);
        d.drawCircle(370, 200, 150);
        d.setColor(Color.WHITE);
        d.drawLine(220, 200, 520, 200);
        d.drawLine(370, 50, 370, 350);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks;
    }

    @Override
    public Point getStartPaddle() {
        return new Point(320, 550);
    }

    @Override
    public Color paddleColor() {
        return Color.YELLOW;
    }

    @Override
    public Color colorOfBalls() {
        return Color.WHITE;
    }
}
