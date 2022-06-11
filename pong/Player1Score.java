package pong;

import basicgraphics.BasicFrame;
import basicgraphics.Clock;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Player1Score extends Sprite
{
    
    Font monospaced = new Font(Font.MONOSPACED,
                               Font.BOLD,
                               50);
    static int score = 0;
    static int prevScore = score;
    
    public Player1Score(SpriteComponent sc) 
    {
        super(sc);
        BufferedImage bi = BasicFrame.createImage(100,
                                                  75);
        Graphics2D scoreDisplay = (Graphics2D) bi.createGraphics();
        scoreDisplay.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                                      RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        scoreDisplay.setColor(Color.CYAN);
        setX(598);
        setY(Pong.getScreenHeight() / 10);
        scoreDisplay.drawString("Blue Score",
                                10,
                                10);
        scoreDisplay.setFont(monospaced);
        scoreDisplay.drawString(this.score + "",
                                25,
                                50);
        Picture pad = new Picture(bi);
        setPicture(pad);
        
        Task w = new Task() 
        {

            @Override
            public void run() 
            {
                if (score != prevScore) 
                {
                    prevScore = score;
                    BufferedImage bi = BasicFrame.createImage(100,
                                                              75);
                    Graphics2D scoreDisplay = (Graphics2D) bi.createGraphics();
                    scoreDisplay.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                                                  RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                    scoreDisplay.setColor(Color.CYAN);
                    setX(598);
                    setY(Pong.getScreenHeight() / 10);
                    scoreDisplay.drawString("Blue Score",
                                            10,
                                            10);
                    scoreDisplay.setFont(monospaced);
                    scoreDisplay.drawString(score + "",
                                            25,
                                            50);
                    Picture pad = new Picture(bi);
                    setPicture(pad);
                }
            }
            
        };
        Clock.addTask(w);
    } 
    
}
