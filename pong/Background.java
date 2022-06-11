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
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Background extends Sprite
{
    private final Graphics2D background;
    private final Picture bg;
    
    public Background(SpriteComponent sc) 
    {
        super(sc);
        BufferedImage im = BasicFrame.createImage(Pong.getScreenWidth(),
                                                  Pong.getScreenHeight());
        background = (Graphics2D) im.createGraphics();
        background.setColor(Color.BLACK);
        super.setX(0);
        super.setY(0);
        background.fillRect(0,
                            0,
                            Pong.getScreenWidth(),
                            Pong.getScreenHeight());
        bg = new Picture(im);
        setPicture(bg);
    }
}
