package pong;

import basicgraphics.BasicFrame;
import basicgraphics.Clock;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Player1 extends Sprite
{
    Picture pad,
            pad2;
    Graphics2D paddle;
    int startingY = ((Pong.getScreenHeight() - 134) / 2) - 75;
    
    public Player1(SpriteComponent sc) 
    {
        super(sc);
        BufferedImage bi = BasicFrame.createImage(40,
                                                  200);
        paddle = (Graphics2D) bi.createGraphics();
        paddle.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        paddle.setColor(Color.CYAN);
        setX(50);
        setY(startingY);
        paddle.fillRect(0,
                        0,
                        (int) super.getX(),
                        (int) super.getY());
        pad = new Picture(bi);
        setPicture(pad);
        
        Task barrier = new Task() 
        {
            @Override
            public void run() {
                if (getY() > 600) 
                {
                    setY(600);
                }
                if (getY() < 0) 
                {
                    setY(0);
                }
            }

        };
        Clock.addTask(barrier);
    }
}