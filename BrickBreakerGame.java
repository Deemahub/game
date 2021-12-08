/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JFrame;

/**
 *
 * @author hp
 */
public class BrickBreakerGame {

    
    public static void main(String[] args) {
        
        JFrame obj = new JFrame(); //  object for JFrame class
        GamePlay gamePlay = new GamePlay();  // object for GamePlay class
        obj. setBounds(10,10,700,600); //window position(x,y) - توسيع الصفحه (width,hieght)
        obj.setTitle(" Brick Breaker Game ");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
        
        
        
    }
    
}