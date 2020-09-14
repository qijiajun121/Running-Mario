package ui;

import mario.mario;
import object.brick;
import object.cBrick;
import object.object;
import object.pipe;
import util.layoutReader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class window extends JFrame {
    public backGround bgd = new backGround();
    public object brick, cBrick, pipe;
    public ArrayList<object> objects = new ArrayList<object>();
    public mario mar;

    public int layOut[][] = null;

    {
        layoutReader lr = new layoutReader();
        try {
            layOut = lr.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public window() {
        this.setSize(800, 450);
        this.setTitle("runningMario");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        mar = new mario(this);

        for (int i = 0; i < layOut.length; i++) {
            for (int j = 0; j < layOut[i].length; j++) {
                if (layOut[i][j] == 1) {
                    cBrick = new cBrick(j * 30, i * 30, 30, 30,
                            new ImageIcon("image/cBrick.png").getImage());
                    objects.add(cBrick);
                }
                if (layOut[i][j] == 2) {
                    brick = new brick(j * 30, i * 30, 30, 30,
                            new ImageIcon("image/brick.png").getImage());
                    objects.add(brick);
                }
                if (layOut[i][j] == 3) {
                    pipe = new cBrick(j * 30, i * 30, 60, 120,
                            new ImageIcon("image/pipe.png").getImage());
                    objects.add(pipe);
                }

            }
        }

        mar.start();

        new Thread(){
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }



    public void paint(Graphics gra){
        BufferedImage bf = (BufferedImage) this.createImage(this.getWidth(), this.getHeight());
        Graphics gr = bf.getGraphics();
        gr.drawImage(bgd.img, bgd.x, bgd.y, null);

        for(int i=0; i<objects.size(); i++){
            object obj = objects.get(i);
            gr.drawImage(obj.img, obj.x, obj.y, obj.width, obj.height, null);
        }
        gr.drawImage(mar.img, mar.x, mar.y, mar.width, mar.height,null);
        gra.drawImage(bf, 0, 0, null);

    }

}



