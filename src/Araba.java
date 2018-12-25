import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.*;
import java.awt.geom.Area;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.Rectangle2D;

class Araba extends JPanel{
    private Image img;
    private ImageIcon image;
    private String path=new ANA_SINIF().getPath();
    protected int x=40, y=400;
    private int dx=0, dy=0;
    Araba(){
        image=new ImageIcon(path+"car3.png");
        img=image.getImage();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void hareketEttir() {
        x += dx;
        y += dy;
    }
    public void durdur() {
        dx=0;
        dy=0;
        x += dx;
        y += dy;
    }
    public void tusBasildi(KeyEvent e) {
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_SPACE){
            dx=3;
        }
    }

    public void tusBirakildi(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if(key==KeyEvent.VK_SPACE){
            dx=0;
        }
    }
}