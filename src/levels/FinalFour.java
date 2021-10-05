package levels;

import basicobjects.Ball;
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
 * level 4.
 */
public class FinalFour implements LevelInformation {
    public static final int DX = 15;
    public static final int DY = 10;
    private int blocks;

    @Override
    public int numberOfBalls() {
        return 3;
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
        return 11;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rect = new Rectangle(new Point(0, 0), 800, 600);
        rect.setColor(new Color(123, 172, 151));
        return new Block(rect);
    }

    @Override
    public Color paddleColor() {
        return new Color(79, 100, 144);
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

    @Override
    public List<Block> createBlocks(List<Block> blocksList, int x, int y, int width, int height,
                                    Color color, int size) {
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
    public List<Color> createColors() {
      List<Color> colorList = new ArrayList<>();
      colorList.add(0, new Color(54, 137, 101));
      colorList.add(1, new Color(143, 222, 114));
      colorList.add(2, new Color(134, 205, 190));
      colorList.add(3, new Color(167, 104, 203));
      colorList.add(4, new Color(229, 142, 111));
      colorList.add(5, new Color(203, 170, 104));
      colorList.add(6, new Color(182, 159, 147));
      return colorList;
    }

    @Override
    public boolean isDirectHit() {
        return false;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // create point all over the screen
        List<Ball> balls = new ArrayList<>();
        int x = 50;
        int y = 70;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Ball temp = new Ball(new Point(x + i * 100, y + j * 100), 10, Color.WHITE);
                balls.add(temp);
            }
        }
        x = 95;
        y = 115;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Ball temp = new Ball(new Point(x + i * 100, y + j * 100), 10, Color.WHITE);
                balls.add(temp);
            }
        }
        for (int i = 0; i < balls.size(); i++) {
            d.setColor(Color.white);
            d.fillCircle(balls.get(i).getX(), balls.get(i).getY(), balls.get(i).getR());
        }

    }



        @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        List<Color> colorList = createColors();
        int x = 775;
        int y = 100;
        int width = 50;
        int height = 30;
        for (int i = 0; i < 7; i++) {
            createBlocks(blockList, x, y + i * height, width, height, colorList.get(i), 15);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks;
    }

    @Override
    public Point getStartPaddle() {
        return new Point(300, 550);
    }
}
