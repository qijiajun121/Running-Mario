package mario;

import object.object;
import object.cBrick;
import ui.window;

import javax.swing.*;
import java.awt.*;

public class mario extends Thread {
    public int x = 0, y = 358, width = 30, height = 32;
    public int xSpeed = 5, ySpeed = 1;
    public window win;
    public Image img = new ImageIcon("image/rRight1.png").getImage();
    public boolean left = false, right = false, up = false, down = false;
    public boolean ground = true;
    public boolean hasGravity = false;


    public mario(window win) {
        this.win = win;
        this.gravity();
    }

    public void run() {
        while (true) {
            if (right) {
                if (hit("Right")) {
                    this.xSpeed = 0;
                }
                if (this.x <= 400) {
                    this.x += this.xSpeed;
                    this.img = new ImageIcon("image/rRight.gif").getImage();
                }
                if (this.x > 400) {
                    win.bgd.x -= this.xSpeed;
                    for (int i = 0; i < win.objects.size(); i++) {
                        object obj = win.objects.get(i);
                        obj.x -= this.xSpeed;
                    }
                }
                this.xSpeed = 5;
            }

            if (left) {
                if (hit("Left")) {
                    this.xSpeed = 0;
                }

                if (this.x >= 0) {
                    this.x -= this.xSpeed;
                    this.img = new ImageIcon("image/rLeft.gif").getImage();
                }
                this.xSpeed = 5;
            }

            if (up) {
                if (ground && !hasGravity) {
                    ground = false;
                   new Thread() {
                       public void run() {
                           jump();
                           ground = true;
                       }
                   }.start();
                }
            }

            try {
                this.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void jump(){
        int height = 0;
        for(int i=0; i<160; i++){
            win.mar.y -= ySpeed;
            height++;
            if(hit("Up")){
                break;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int j=0; j<height; j++){
            win.mar.y += ySpeed;
            if(hit("Down")){
                this.ySpeed = 0;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.ySpeed = 1;
    }


    public boolean hit(String dir) {
        Rectangle rec = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle rec1 = null;
        for(int i=0; i< win.objects.size(); i++) {
            object obj = win.objects.get(i);

            switch (dir){
                case "Right":
                    rec1 = new Rectangle(obj.x-2, obj.y, obj.width, obj.height);
                    break;
                case "Left":
                    rec1 = new Rectangle(obj.x+2, obj.y, obj.width, obj.height);
                    break;
                case "Up":
                    rec1 = new Rectangle(obj.x, obj.y+2, obj.width, obj.height);
                    break;
                case "Down":
                    rec1 = new Rectangle(obj.x, obj.y-2, obj.width, obj.height);
                    break;
            }

            if(dir.equals("Up")&&rec.intersects(rec1)){
                //System.out.println(obj.getClass());
                if(obj.getClass() == cBrick.class){
                    obj.img = new ImageIcon("image/cBrick1.png").getImage();
                }
            }

            if(rec.intersects(rec1)){
                return true;
            }
        }
        return false;
    }

    public void gravity(){
        new Thread(){
            public void run() {
                while (true) {
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (true) {
                        if (!ground) {
                            break;
                        }
                        if (hit("Down")) {
                            break;
                        }
                        if (y >= 358) {
                            hasGravity = false;
                        } else {
                            hasGravity = true;
                            y += ySpeed;
                        }

                        try {
                            sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
}
