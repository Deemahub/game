


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class maping {
    
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    // constructor
    public maping (int row , int col){
    map = new int [row][col];
      for(int i=0;i<map.length;i++){
          for (int j=0;j<map[0].length;j++) {
             map [i][j] = 1;
                        } //end iner for
              }//end for
    brickWidth = 540/col ;
    brickHeight = 150/row ;
     }//end constructor
    
    // draw bricks
   public void draw(Graphics2D g) {
      for(int i=0;i<map.length;i++){
        for (int j=0;j<map[0].length;j++) {
            if( map [i][j] > 0){
            g.setColor(Color.pink);
            g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight); // (x,y,Width,Height)
           
            // create a border araund each bricks
            g.setStroke(new BasicStroke(3));
            g.setColor(Color.WHITE);
            g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
               }//end if
                        } //end iner for
              }//end for 
   } //end "draw(Graphics2D g)" method
   
    public void setBrickValue(int value,int row, int col){
       map[row][col] = value;
       
       } // end setBrickValue method
}  // end class maping