/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JOptionPane;


public class Ball extends Sprite 
{
    private Picture ball,
                    ball2;
    private Random rand = new Random();
    private final int[] DIRECTION = new int[] {-5, 5};
    private int random;
    private int[] possibleYs = new int[] {-5, -6, -7};
    private Graphics2D circle;
    private BufferedImage bi;
    private int scoreLimit = Pong.scoreLimit > 0 ? Pong.scoreLimit : 3;
    private ReusableClip hitSound =  new ReusableClip("hit.wav");
    private ReusableClip scoreSound =  new ReusableClip("score.wav");
    boolean started;
    
    public Ball(SpriteComponent sc) 
    {
        super(sc);
        bi = BasicFrame.createImage(40,
                                    40);
        circle = (Graphics2D) bi.createGraphics();
        circle.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        circle.setColor(Color.WHITE);
        setX((Pong.getScreenWidth() / 2) - 10);
        setY((Pong.getScreenHeight() - 134) / 2);
        circle.fillRect(0,
                        0,
                        (int) super.getX(),
                        (int) super.getY());
        ball = new Picture(bi);
        setPicture(ball);
    }
    
    public int getScoreLimit() 
    {
        return scoreLimit;
    }

    public void setScoreLimit(int scoreLimit) 
    {
        this.scoreLimit = scoreLimit;
    }
    
    /**
     * Resets the playing field
     */
    public void kickoff() 
    {
        setVelX(0);        
        setVelY(0);
        started = false;
    }
    
    /**
     * Starts the series, kicking the ball off in a random direction.
     */
    public void start() 
    {
        random = rand.nextInt(2);
        setVelX(DIRECTION[random]);        
        setVelY(-5);
        started = true;
    }
    
    @Override
    /**
     *  Sets the ball back to the middle of the screen
     *  If ball hits xlo/xhi, changes score
     *  Win conditions
     */
    public void processEvent(SpriteCollisionEvent col) 
    {
        Random ran = new Random();
        if (col.xlo) 
        {
            
            scoreSound.play();
            //Ball Reseter
            setX(Pong.getScreenWidth() / 2);
            setY((Pong.getScreenHeight() - 134) / 2);
            
            //Score Changer
            Player2Score.score++;

            //Win condition & Replay Prompt
            if (Player2Score.score == scoreLimit) 
            {
                kickoff();
                if (JOptionPane.showConfirmDialog(null,
                                                  "Wow, you let a computer beat you, goodjob man... \nDo you want to play again?",
                                                  "GAME OVER",
                                                  JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
                {
                    Pong.aiWins++;
                    Player1Score.score = 0;
                    Player2Score.score = 0;
                    
                } 
                else 
                {
                    System.exit(1);
                }     
            }
            kickoff(); 
        }
        if (col.xhi) 
        { 
            
            scoreSound.play();
            //Ball Reseter
            setX(Pong.getScreenWidth() / 2);
            setY((Pong.getScreenHeight() - 134) / 2);
            
            //Score Changer
            Player1Score.score++;
            
            //Win Condition & Replay Prompt
            if (Player1Score.score == scoreLimit) 
            {
                kickoff();
                if (JOptionPane.showConfirmDialog(null,
                                                  "Player 1 Wins!! \nDo you want to play again?",
                                                  "GAME OVER",
                                                  JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
                {
                    Pong.playerWins++;
                    Player1Score.score = 0;
                    Player2Score.score = 0;
                } 
                else 
                {
                    System.exit(1);
                }
            }
            kickoff();
        }
        //These two if-statements make the ball go bounce off of 
        //the ceiling & floor at random angles
        if (col.ylo) 
        { 
            hitSound.play();
            setVelY(possibleYs[ran.nextInt(3)] * -1);
        }
        if (col.yhi) 
        { 
            hitSound.play();
            setVelY(possibleYs[ran.nextInt(3)] * 1);
        }
    }
}