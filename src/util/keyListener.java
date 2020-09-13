package util;

import ui.window;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyListener extends KeyAdapter{
    public window win;

    public keyListener(window win){
        this.win = win;
    }

    public void keyPressed(KeyEvent e){
        int number = e.getKeyCode();
        switch(number){
            case 39:
                win.mar.right = true;
                break;
            case 37:
                win.mar.left = true;
                break;
            case 38:
                win.mar.up = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e){
        int number = e.getKeyCode();
        switch(number){
            case 39:
                win.mar.right = false;
                win.mar.img = new ImageIcon("image/rRight1.png").getImage();
                break;
            case 37:
                win.mar.left = false;
                win.mar.img = new ImageIcon("image/rLeft1.png").getImage();
                break;
            case 38:
                win.mar.up = false;
                break;
        }
    }

}
