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
 * level 3.
 */
public class Three implements LevelInformation {
    public static final int DX = 10;
    public static final int DY = 5;
    private int blocks;
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(i, Velocity.fromAngleAndSpeed(DX + 15 * i, (double) DY));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "3 Blue";
    }

    @Override
    public Sprite getBackground() {
        gameobjects.Rectangle rect = new Rectangle(new Point(0, 0), 800, 600);
        rect.setColor(new Color(10, 32, 172));
        return new Block(rect);
    }
    @Override
    public List<Color> createColors() {
        List<Color> colorList = new ArrayList<>();
        colorList.add(0, new Color(222, 16, 16));
        colorList.add(1, new Color(212, 226, 11));
        colorList.add(2, new Color(19, 218, 224));
        colorList.add(3, new Color(17, 186, 12));
        colorList.add(4, new Color(196, 196, 196));
        return colorList;
    }

    @Override
    public boolean isDirectHit() {
        return false;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // create the image
        d.setColor(Color.black);
        d.fillRectangle(50, 400, 110, 200);
        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(60 + (j * 20), 410 + (i * 38), 10, 28);
            }
        }
        d.setColor(java.awt.Color.DARK_GRAY);
        d.fillRectangle(90, 330, 30, 70);
        d.setColor(java.awt.Color.GRAY);
        d.fillRectangle(100, 160, 10, 170);
        d.setColor(java.awt.Color.yellow);
        d.fillCircle(105, 148, 12);
        d.setColor(java.awt.Color.RED);
        d.fillCircle(105, 148, 8);
        d.setColor(java.awt.Color.WHITE);
        d.fillCircle(105, 148, 4);
        // create the moon
        int radios = 30;
        int x = 720;
        int y = 100;
        d.setColor(new Color(120, 115, 110));
        d.fillCircle(x, y, radios);
        d.setColor(new Color(156, 150, 146));
        d.fillCircle(x - 10, y, radios);
        d.setColor(Color.BLACK);
        d.drawCircle(x - 10, y, radios);
    }

    @Override
    public List<Block> createBlocks(List<Block> blocksList, int x, int y,
                                    int width, int height, Color color, int size) {
        for (int i = 0; i < size; i++) {
            x = x - width;
            Rectangle rectangle = new Rectangle(new Point(x, y), width, height);
            rectangle.setColor(color);
            blocksList.add(new Block(rectangle));
        }
        this.blocks = blocksList.size();
        return blocksList;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        List<Color> colorList = createColors();
        int x = 775;
        int y = 150;
        int width = 60;
        int height = 25;
        int eachRow = 10;
        for (int i = 0; i < 5; i++) {
            createBlocks(blockList, x, y + i * height, width, height, colorList.get(i), eachRow - i);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks;
    }

    @Override
    public Point getStartPaddle() {
        return new Point(310, 550);
    }

    @Override
    public Color paddleColor() {
        return new Color(231, 79, 20);
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
