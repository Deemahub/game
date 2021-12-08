


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;//
import java.awt.event.ActionListener;// 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;// لاستخدام الاسهم في تحريك السلايدر 

import javax.swing.Timer;

import javax.swing.JPanel;


// KeyListener for arrow keys to moving the slaider , ActionListener for moving the ball
public class GamePlay extends JPanel implements KeyListener , ActionListener  {
    
    private boolean play = false ; // عشان ماتبدا اللعبة على طول
    private int score =0 ;
    
     
     
     private Timer timer ; // Timer class for seting the time of ball that how fast moving
     private int delay = 5 ; // سرعة تحريك الكرة لاقللت بيصير اسرع
     
      // properties for the x-axis and y-axis of the slider and a ball
      private int playerX = 310; // 310 is starting position of slider
      // ball direction
      private int ballposX =120;  // 120 is starting position of ball for x-axis
      private int ballposY =350;   //  350 is starting position of ball for y-axis اذا زدت بينزل تحت
      private int ballXdir = -1;   // X direction of the ball
      private int ballYdir = -2;   //Y  direction of the ball
      
      private int level=1;
      private int totalBricks = 4 ;
      private maping map ; // object for maping class
      
      // constructor
      public GamePlay(){
          map = new maping(2,2); // maping(row,column)
          addKeyListener(this);
          setFocusable(true);// it just gives the ability to potentially gain the focus to the component.sets or gets the focusable state of the component. A component must be focusable in order to gain the focus. When a component has been removed from the focus cycle with setFocusable(false) , it can no longer be navigated with the keyboard
          setFocusTraversalKeysEnabled(false);// setFocusTraversalKeysEnabled() decides whether or not focus traversal keys (TAB key, SHIFT+TAB, etc.) are allowed to be used when the current Component has focus. For example:
          timer = new Timer(delay,this); // 
          timer.start(); // to start moving
      
           }//end GamePlay constructor
      
      // drw different shapes like slider and balland the tiles 
      public void paint(Graphics g){  
      // background
          g.setColor(Color.WHITE);  //background Color
          g.fillRect(1, 1, 692, 592);  // rectangle for the background, (position,position,size,size)
          
          // drawing map
          map.draw((Graphics2D)g);
          
      // borders
          g.setColor(Color.LIGHT_GRAY); // borders Color
          g.fillRect(0, 0, 3, 592); // rectangle for the border left(margin,longdown,width,longup)
          g.fillRect(0, 0, 692, 3); // (longleft,margin,longright,width)
          g.fillRect(691, 0, 3, 592);
          // we do not add border at the down side because we want a ball to move down for end the game
      
      // scores
          g.setColor(Color.BLACK);
          g.setFont(new Font("serif",Font.BOLD,25));
          g.drawString("score: "+score, 550, 30);  //score location(x,y)
          
       // levels
          g.setColor(Color.BLACK);
          g.setFont(new Font("serif",Font.BOLD,25));
          g.drawString("level: "+level, 450, 30);  //level location
          
      // paddle - slider
          g.setColor(Color.CYAN);
          g.fillRect(playerX , 550, 100, 8);//(pos y, long,width)
          
      //  ball
          g.setColor(Color.LIGHT_GRAY);
          g.fillOval(ballposX , ballposY, 20, 20);//(width,highet)
          
          //if we finish our game
          if (level==1){
          if (totalBricks <= 0){
          play =false;
          ballXdir = 0 ;
          ballYdir = 0 ;
          g.setColor(Color.green);
          g.setFont(new Font("serif",Font.BOLD,30));
          g.drawString("You Won, Scores: "+score,190, 300);  // "You Won, Scores: " location(x,y)
         // level ++;
          g.setFont(new Font("serif",Font.BOLD,20));
          g.drawString("Press ALT to go next level", 210, 350);  // "Press ALT to go next level" location(x,y)
          }//end if
          }
          
          
          if (level>= 2){
          if (totalBricks <= 0){
          play =false;
          ballXdir = 0 ;
          ballYdir = 0 ;
          g.setColor(Color.green);
          g.setFont(new Font("serif",Font.BOLD,30));
          g.drawString("You Won, Scores: "+score,190, 300);  // "You Won, Scores: " location
         
          }//end if
          }
          
          // lose game
          if (ballposY > 570){
          play =false;
          ballXdir = 0 ;
          ballYdir = 0 ;
          g.setColor(Color.RED);
          g.setFont(new Font("serif",Font.BOLD,30));
          g.drawString("Game Over, Scores: "+score,190, 300);  // "Game Over, Scores: " location
          
          g.setFont(new Font("serif",Font.BOLD,20));
          g.drawString("Press Enter to Restart", 230, 350);  // "Press Enter to Restart" location
          }//end if
    
          g.dispose(); // dispose() on a JFrame will destroy the window and have the operating system clean up after it, if its the only JFrame left, the Java VM might terminate.
      
      }
      
      
    @Override
    public void actionPerformed(ActionEvent ae) { //ae is a type of ActionEvent, it allows you to access the properties of the ActionEvent. The actionPerformed method is called when the associated object generates a action, the ActionEvent carries the properties if the action which help you identify what you should do.
      timer.start();  // started the timer
        if(play){
        if(new Rectangle(ballposX , ballposY, 20, 20).intersects(new Rectangle(playerX , 550, 100, 8))){
        ballYdir = -ballYdir ;
        }// end iner 1 if  , عشان الكورة ترتد اذا لمست السلايدر
        
        
        // A is label like switch
        A: for(int i=0;i<map.map.length;i++){     
            for (int j=0;j<map.map[0].length;j++){
                 if (map.map[i][j] > 0){
                    int brickX = j * map.brickWidth+80; // ball touch bricks
                    int brickY = i * map.brickHeight+50;
                    int brickWidth =map.brickWidth;
                    int brickHeight =map.brickHeight;
                 
                    Rectangle rect =new Rectangle(brickX,brickY,brickWidth,brickHeight);
                    Rectangle ballRect =new Rectangle(ballposX,ballposY,20,20);
                    Rectangle brickRect = rect;
                                      
                   // اذا الكورة ضربت   طوبة   راح تتغير قيمته من واحد الى صفر وتختفي
                    if (ballRect.intersects(brickRect)){ 
                       map.setBrickValue(0, i, j);
                       totalBricks--;
                       score += 5 ; // score in each hit
                       if (ballposX +19 <=brickRect.x || ballposX +1 >=brickRect.x+brickRect.width){
                          ballXdir = -ballXdir;
                        }// end iner if
                       else{
                           ballYdir = -ballYdir;
                       }//end else
                          break A;
           
                     }//end outer if
                 
                 
                 }//end last outer if
         }//end iner for
         }//end outer for 
        
        ballposX += ballXdir;
        ballposY += ballYdir;
        if(ballposX <0){
        ballXdir = -ballXdir ;
        } // end iner 2 if  , عشان الكورة ترتد اذا لمست  الحافة اليسار
        if(ballposY <0){
        ballYdir = -ballYdir ;
        }// end iner 3 if  , عشان الكورة ترتد اذا لمست  الحافة العلوية
        if(ballposX > 670){
        ballXdir = -ballXdir ;
        }// end iner 4 if  , عشان الكورة ترتد اذا لمست  الحافة اليمين
         } // end outer if 
         repaint();  // recall " paint(Graphics g)" method
    }
    

    @Override
    public void keyTyped(KeyEvent ke) { }
    @Override
    public void keyReleased(KeyEvent ke) {  }
    

    // to detect the arrow keys
    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()== KeyEvent.VK_RIGHT){  // detect the RIGHT key
           if (playerX >= 600){ // how mutch moving when press right key
             playerX = 600;
               }// end iner if
           else {
             moveRight();
               } // end else 
        }// end outer if
        
        if(ke.getKeyCode()== KeyEvent.VK_LEFT){   // detect the LEFT key
           if (playerX <10){ 
             playerX = 10;
               }// end iner if
           else {
             moveLeft();
               } // end else 
        }// end outer if
        
        //detect Enter event
        
        if (level==1){
        if(totalBricks > 0){
        if(ke.getKeyCode()== KeyEvent.VK_ENTER){
           // if Press Enter it will be Restart level 1
           if (!play){
           play = true; 
           score =0 ;
           playerX = 310; // 310 is starting position of slider
      
           ballposX =120;  // 120 is starting position of ball for x-axis
           ballposY =350;   // 350 is starting position of ball for y-axis
           ballXdir = -1;   // X direction of the ball
           ballYdir = -2 ;   //Y  direction of the ball
           totalBricks = 6 ;
           map = new maping(2,2); //maping(row,column)
            
            repaint();
          }//end iner if
         }//end outer if
        }  }
        
        if (level>1){
        if(totalBricks > 0){
        if(ke.getKeyCode()== KeyEvent.VK_ENTER){
           // if Press Enter it will be Restart level 2
           if (!play){
           play = true; 
           score =0 ;
           playerX = 310; // 310 is starting position of slider
      
           ballposX =120;  // 120 is starting position of ball for x-axis
           ballposY =350;   // 350 is starting position of ball for y-axis
           ballXdir = -1;   // X direction of the ball
           ballYdir = -2 ;   //Y  direction of the ball
           totalBricks = 6 ;
           map = new maping(2,3); //maping(row,column)
            
            repaint();
          }//end iner if
         }//end outer if
        }  }
        
        //detect ALT event
        
        if (level==1){
        if (totalBricks <= 0){
        if(ke.getKeyCode()== KeyEvent.VK_ALT){
           // if Press ALT it will be go next level
           if (!play){
           play = true; 
           score =0 ;
           playerX = 310; // 310 is starting position of slider
      
           ballposX =120;  // 120 is starting position of ball for x-axis
           ballposY =350;   // 350 is starting position of ball for y-axis
           ballXdir = -1;   // X direction of the ball
           ballYdir = -2 ;   //Y  direction of the ball
           totalBricks = 6 ;
           map = new maping(2,3); //maping(row,column)
           
           level ++;
            repaint();
          }//end iner if
         }//end outer if
        }  }
       }//end keyPressed method
    
    
     public void moveRight(){
          play = true;
          playerX += 20; // how long moving when press right arrow
        }
    
     public void moveLeft(){
         play = true;
         playerX -= 20;
       }

   
} // end GamePlay class