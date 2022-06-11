/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class ScoreBoard extends Sprite
{
    
    Font monospaced = new Font(Font.MONOSPACED,
                               Font.PLAIN,
                               30);
    private int prevPlayerWins = Pong.playerWins;
    private int prevAIWins = Pong.aiWins;
    
    public ScoreBoard(SpriteComponent sc) 
    {
        super(sc);
        BufferedImage bi = BasicFrame.createImage(1000,
                                                  500);
        Graphics2D scoreDisplay = (Graphics2D) bi.createGraphics();
        
        //Text Quality
        scoreDisplay.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                      RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        
        scoreDisplay.setColor(Color.WHITE);
        setX(718);
        setY(Pong.getScreenHeight() / 15);
        scoreDisplay.setFont(monospaced);
        scoreDisplay.drawString("Record",
                                0,
                                20);
        scoreDisplay.drawString(Pong.playerWins + " - " + Pong.aiWins,
                                9,
                                50);
        Picture pad = new Picture(bi);
        setPicture(pad);
        
        Task w = new Task() 
        {

            @Override
            //Updates Record
            public void run() 
            {
                if (Pong.playerWins != getPrevPlayerWins()) 
                {
                    setPrevPlayerWins(Pong.playerWins);
                    BufferedImage bi = BasicFrame.createImage(1000,
                                                              500);
                    Graphics2D scoreDisplay = (Graphics2D) bi.createGraphics();

                    //Text Quality
                    scoreDisplay.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                                  RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                    scoreDisplay.setColor(Color.WHITE);
                    setX(718);
                    setY(Pong.getScreenHeight() / 15);
                    scoreDisplay.setFont(monospaced);
                    scoreDisplay.drawString("Record",
                                            0,
                                            20);
                    scoreDisplay.drawString(Pong.playerWins + " - " + Pong.aiWins,
                                            9,
                                            50);
                    Picture pad = new Picture(bi);
                    setPicture(pad);
                }
                if (Pong.aiWins != getPrevAIWins()) 
                {
                    setPrevAIWins(Pong.aiWins);
                    BufferedImage bi = BasicFrame.createImage(1000,
                                                              500);
                    Graphics2D scoreDisplay = (Graphics2D) bi.createGraphics();

                    //Text Quality
                    scoreDisplay.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                                  RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                    scoreDisplay.setColor(Color.WHITE);
                    setX(720);
                    setY(Pong.getScreenHeight() / 15);
                    scoreDisplay.setFont(monospaced);
                    scoreDisplay.drawString("Record",
                                            0,
                                            20);
                    scoreDisplay.drawString(Pong.playerWins + " - " + Pong.aiWins,
                                            9,
                                            50);
                    Picture pad = new Picture(bi);
                    setPicture(pad);
                }
            }
        };
        Clock.addTask(w);
    }

    public int getPrevPlayerWins() 
    {
        return prevPlayerWins;
    }

    public void setPrevPlayerWins(int prevPlayerWins) 
    {
        this.prevPlayerWins = prevPlayerWins;
    }

    public int getPrevAIWins() 
    {
        return prevAIWins;
    }

    public void setPrevAIWins(int prevAIWins) 
    {
        this.prevAIWins = prevAIWins;
    }
}
