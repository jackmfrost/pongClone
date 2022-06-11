/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;



public class Watermark extends Sprite
{
    Font monospaced = new Font(Font.MONOSPACED,
                               Font.PLAIN,
                               15);
    
    public Watermark(SpriteComponent sc) 
    {
        super(sc);
        BufferedImage bi = BasicFrame.createImage(300,
                                                  100);
        Graphics2D scoreDisplay = (Graphics2D) bi.createGraphics();
        
        //Text Quality
        scoreDisplay.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                      RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        scoreDisplay.setColor(Color.WHITE);
        setX(670);
        setY(Pong.getScreenHeight() - 100);
        scoreDisplay.setFont(monospaced);
        scoreDisplay.drawString("A Jack Frost Production",
                                0,
                                20);
        Picture pad = new Picture(bi);
        setPicture(pad);
    }
}
