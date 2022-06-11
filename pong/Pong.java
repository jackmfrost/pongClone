/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import basicgraphics.BasicFrame;
import basicgraphics.Clock;
import basicgraphics.SpriteComponent;
import basicgraphics.SpriteSpriteCollisionListener;
import basicgraphics.Task;
import basicgraphics.sounds.ReusableClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author jackm
 */
public class Pong 
{

    static int              scoreLimit;
    static int              aiSpeed;
    static int              playerSpeed;
    static int              topSpeed;
    static int              playerWins;
    static int              aiWins;
    static int              gameMode;
    static BasicFrame       bf;
    static ReusableClip     hitSound =  new ReusableClip("hit.wav");
    
    //Game modes
    static final int        SINGLEPLAYER = 0;
    static final int        SPECTATOR = 1;
    static final int        MULTIPLAYER = 2;
    static final String[]   gamemodes = {"SINGLEPLAYER",
                                         "SPECTATOR",
                                         "MULTIPLAYER" };
    
    
    
    /***
     * Gets the width of the resolution of the user's monitor.
     * @return width of monitor
     */
    public static int getScreenWidth() 
    {
        Dimension size = Toolkit.getDefaultToolkit()
                                .getScreenSize();
        int width = (int) size.getWidth();
        return (width);
    }
    
    /***
     * Gets the height of the resolution of the user's monitor.
     * @return height of monitor
     */
    public static int getScreenHeight() 
    {
        Dimension size = Toolkit.getDefaultToolkit()
                                .getScreenSize();
        int height = (int) size.getHeight();
        return (height);
    }
    
    /***
     * Sets the window icon
     */
    public static void setIcon() 
    {
        ImageIcon img = new ImageIcon("C:\\Users\\jackm\\Documents\\NetBeansProjects\\BasicGraphics\\src\\pong\\Pong.png");
        bf.jf.setIconImage(img.getImage());
    }
    
    public static void main(String[] args) 
    {

        //Frame Setup
        bf = new BasicFrame("Pong 2022");
        SpriteComponent sc = new SpriteComponent();
        Dimension dim = new Dimension(getScreenWidth(),
                                      getScreenHeight());
        setIcon();
        sc.setPreferredSize(dim);
        bf.createBasicLayout(sc);
        bf.show();
        
        //Sets screen size to maximized by default
        bf.jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Makes background black
        Background background = new Background(sc);
        
        ///////////////////////////Pregame Settings/////////////////////////////
        
        //Game Mode Select
        switch (JOptionPane.showOptionDialog(sc,
                                             "Please select a game mode.",
                                             "Game Mode Select",
                                             JOptionPane.OK_CANCEL_OPTION,
                                             JOptionPane.PLAIN_MESSAGE, new ImageIcon(),
                                             gamemodes,
                                             SINGLEPLAYER)) 
        {
            case (SINGLEPLAYER):
                gameMode = SINGLEPLAYER;
                break;
            case (MULTIPLAYER):
                gameMode = MULTIPLAYER;
                scoreLimit = 3;
                playerSpeed = 9;
                topSpeed = 20;
                break;
            case (SPECTATOR):
                gameMode = SPECTATOR;
                scoreLimit = 3;
                aiSpeed = 7;
                playerSpeed = 9;
                topSpeed = 17;
                break;
        }

        if (gameMode == SINGLEPLAYER) 
        {
            //Score Limit Setting Prompt, if input is invalid scoreLimit is set to 3
            try 
            {
                scoreLimit = Integer.parseInt(JOptionPane.showInputDialog(sc, 
                            "Enter your desired score limit. "
                            + "\nIf input is less than or equal to 0, "
                            + "it will be set to the default (3)"));
            }
            catch (Exception NumberFormatException) {
                scoreLimit = 3;
            }

            //Hard Mode Prompt
            if (JOptionPane.showConfirmDialog(sc, 
                                              "Hard Mode?\n(A.I. speed will be increased)", "HARD MODE?",
                                              JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
            {
                aiSpeed = 8;
                playerSpeed = 9;
                topSpeed = 20;
            } 
            else {
                aiSpeed = 7;
                playerSpeed = 9;
                topSpeed = 17;
            }
        }
        
        //Controls
        if (gameMode == SINGLEPLAYER) 
        {
            JOptionPane.showConfirmDialog(sc,
                                          "To go up, press the 'W' key or the Up Arrow key\n"
                                          + "To go down, press the 'S' key or the Down Arrow key",
                                          "Controls",
                                          JOptionPane.PLAIN_MESSAGE);
        }
        if (gameMode == MULTIPLAYER) 
        {
            JOptionPane.showConfirmDialog(sc,
                                          "To go up, press the 'W' key (Player 1) or the Up Arrow key(Player 2)\n"
                                          + "To go down, press the 'S' key (Player 1) or the Down Arrow key (Player 2)",
                                          "Controls",
                                          JOptionPane.PLAIN_MESSAGE);
        }
            
        ////////////////////////////////////////////////////////////////////////
        
        //Creates players and other on screen objects
        ScoreBoard sb = new ScoreBoard(sc);
        Watermark wm = new Watermark(sc);
        Player1 player1 = new Player1(sc);
        Player1Score p1score = new Player1Score(sc);
        Player2 player2 = new Player2(sc);
        Player2Score p2score = new Player2Score(sc);
        Ball ball = new Ball(sc);
        Clock.addTask(sc.moveSprites());
        Clock.start(15);
        
        //Player Controls
        KeyAdapter Controls = new KeyAdapter() 
        {
            
            @Override
            public void keyPressed(KeyEvent ke) 
            {
                int code = ke.getKeyCode();
                if (gameMode == SINGLEPLAYER) 
                {
                    switch (code) 
                    {
                        case (KeyEvent.VK_W):
                            player1.setY(player1.getY() - playerSpeed);
                            if (!(ball.started)) 
                            {
                                ball.start();
                            }
                            break;
                        case (KeyEvent.VK_UP):
                            player1.setY(player1.getY() - playerSpeed);
                            if (!(ball.started)) 
                            {
                                ball.start();
                            }
                            break;
                        case (KeyEvent.VK_S):
                            player1.setY(player1.getY() + playerSpeed);
                            if (!(ball.started)) 
                            {
                                ball.start();
                            }
                            break;
                        case (KeyEvent.VK_DOWN):
                            player1.setY(player1.getY() + playerSpeed);
                            if (!(ball.started)) 
                            {
                                ball.start();
                            }
                            break;
                    }
                }
                if (gameMode == MULTIPLAYER) 
                {
                    switch (code) 
                    {
                        case (KeyEvent.VK_W):
                            player1.setY(player1.getY() - playerSpeed);
                            if (!(ball.started)) 
                            {
                                ball.start();
                            }
                            break;
                        case (KeyEvent.VK_UP):
                            player2.setY(player2.getY() - playerSpeed);
                            if (!(ball.started)) 
                            {
                                ball.start();
                            }
                            break;
                        case (KeyEvent.VK_S):
                            player1.setY(player1.getY() + playerSpeed);
                            if (!(ball.started)) 
                            {
                                ball.start();
                            }
                            break;
                        case (KeyEvent.VK_DOWN):
                            player2.setY(player2.getY() + playerSpeed);
                            if (!(ball.started)) 
                            {
                                ball.start();
                            }
                            break;
                    }
                }
            }
        };
        
        //Position Reseter
        Task pos = new Task() 
        {
            @Override
            public void run() 
            {
                if ((p1score.score != p1score.prevScore) || (p2score.score != p2score.prevScore)) {
                    player1.setY(player1.startingY);
                    player2.setY(player2.startingY);
                }
            }  
        };
        Clock.addTask(pos);
        
        //AI
        Task ai = new Task() 
        {
            
            @Override
            public void run() 
            {
                if (gameMode == SINGLEPLAYER) 
                {
                    boolean myTurn;
                    if (ball.getVelX() > 0) {
                        myTurn = true;
                    }
                    else {
                        myTurn = false;
                    }
                    int ballY = (int) ball.getY();
                    int aiY = (int) player2.getY();
                    if (myTurn && ball.getX() >= 350) {
                        if (aiY > ballY) {
                            player2.setY(aiY - aiSpeed);
                        }
                        else if (aiY < ballY){
                            player2.setY(aiY + aiSpeed);
                        }
                    }
                }
                if (gameMode == SPECTATOR) 
                {
                    boolean myTurn;
                    if (!(ball.started)) 
                    {
                        ball.start();
                    }
                    if (ball.getVelX() > 0) 
                    {
                        myTurn = true;
                    }
                    else {
                        myTurn = false;
                    }
                    
                    int ballY = (int) ball.getY();
                    int ai1Y = (int) player1.getY();
                    int ai2Y = (int) player2.getY();
         
                    if (myTurn && ball.getX() >= 350) 
                    {
                        if (ai2Y > ballY) 
                        {
                            player2.setY(ai2Y - aiSpeed);
                        }
                        else if (ai2Y < ballY)
                        {
                            player2.setY(ai2Y + aiSpeed);
                        }
                    }
                    if (ai1Y > ballY) 
                    {
                        player1.setY(ai1Y - aiSpeed);
                    }
                    else if (ai1Y < ballY)
                    {
                        player1.setY(ai1Y + aiSpeed);
                    }
                }
            }   
        };   
        Clock.addTask(ai);
        
        bf.addKeyListener(Controls);
        
        //Collision
        sc.addSpriteSpriteCollisionListener(Player1.class,
                                            Ball.class,
                                            new SpriteSpriteCollisionListener<Player1,Ball>() 
        {
            
            @Override
            public void collision(Player1 sp1,
                                  Ball sp2) 
            {
                int topspeed = Pong.topSpeed;
                if (ball.getVelX() >= topspeed || ball.getVelX() <= topspeed * -1) 
                {
                    ball.setVelX((ball.getVelX() * -1));
                }
                else {
                    ball.setVelX((ball.getVelX() - 2) * -1);
                }
                hitSound.play();
            }
        });
        
        sc.addSpriteSpriteCollisionListener(Player2.class,
                                            Ball.class,
                                            new SpriteSpriteCollisionListener<Player2,Ball>()
        {
            
            @Override
            public void collision(Player2 sp1,
                                  Ball sp2) 
            {
                ball.setVelX((ball.getVelX() * -1));
                hitSound.play();
            }
        }); 
    }
}

//PAUSE
//KICKOFF COUNTDOWN