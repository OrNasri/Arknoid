package levels;

import basicobjects.Point;
import basicobjects.Velocity;
import biuoop.DrawSurface;
import gameobjects.Block;
import gameobjects.Rectangle;
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
public class Two implements LevelInformation {
    public static final int DX = 10;
    public static final double DY = 10;
    private int blocks;
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
           list.add(i, Velocity.fromAngleAndSpeed(DX + 15 * i, DY));
        }
        return list;
    }



    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        gameobjects.Rectangle rect = new Rectangle(new Point(0, 0), 800, 600);
        rect.setColor(Color.WHITE);
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
        List<Color> colorList = createColors();
        int x = 775;
        int y = 300;
        int width = 50;
        int height = 30;
        for (int i = 0; i < 15; i++) {
            x = x - width;
            Rectangle rectangle = new Rectangle(new Point(x, y), width, height);
            rectangle.setColor(colorList.get(i));
            blockList.add(new Block(rectangle));
        }

        this.blocks = blockList.size();
        return blockList;
    }

    @Override
    public List<Color> createColors() {
        List<Color> colorList = new ArrayList<>();
        colorList.add(0, Color.RED);
        colorList.add(1, Color.RED);
        colorList.add(2, Color.ORANGE);
        colorList.add(3, Color.ORANGE);
        colorList.add(4, Color.YELLOW);
        colorList.add(5, Color.YELLOW);
        colorList.add(6, Color.GREEN);
        colorList.add(7, Color.GREEN);
        colorList.add(8, Color.GREEN);
        colorList.add(9, Color.CYAN);
        colorList.add(10, Color.CYAN);
        colorList.add(11, new Color(255, 10, 230));
        colorList.add(12, new Color(255, 10, 230));
        colorList.add(13, new Color(127, 28, 154));
        colorList.add(14, new Color(127, 28, 154));
        return colorList;
    }

    @Override
    public boolean isDirectHit() {
        return false;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int radios = 70;
        int pointVal = 150;
        d.setColor(Color.YELLOW);
        d.fillCircle(pointVal, pointVal, radios);
        for (int i = 0; i < radios; i++) {
            d.drawLine(pointVal, pointVal, i * 12, 600 / 2);
        }
    }


    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks;
    }

    @Override
    public Point getStartPaddle() {
        return new Point(100, 550);
    }

    @Override
    public Color paddleColor() {
        return Color.YELLOW;
    }

    @Override
    public Color colorOfBalls() {
        return Color.WHITE;
    }

    @Override
    public void setDidIt(boolean value) {
    }

    @Override
    public boolean getDidIt() {
        return false;
    }
}
