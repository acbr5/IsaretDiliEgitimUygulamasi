//Programa hareketli gif'lerin yüklenebilmesi için ilgili sınıflardaki gif yolunu değiştiriniz.
// Başka herhangi bir değişiklik gerekmemektedir.
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.*;
import java.awt.geom.Area;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

class Kalemm extends JPanel{
    private int sayac=0;
    private int a=100, b=100, c=130,d=60;
    private String[] siniflar={"DERSLER","HAFTANIN GÜNLERİ","HARFLER","İSİMLER", "KONUŞMALAR", "OYUN"};
    private ArrayList<Area> dikdortgen;
    Kalemm(){
        dikdortgen=new ArrayList<>();
    }
    private void cizimYap(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        Graphics2D g1d=(Graphics2D) g;
        Font font=new Font("SansSerif", Font.PLAIN, 18);

        for(int i=0; i<siniflar.length; i++){
            Area a1=new Area(new Rectangle2D.Double(a, b, c, d));
            g2d.setColor(new Color(165,8,210));
            if(sayac==0){
                g2d.fill(a1);
                dikdortgen.add(a1);
            }

            addMouseListener(new MouseTetikleyici(a1));
            g1d.setColor(new Color(250,250,250));
            g1d.setFont(font);
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            g2d.setRenderingHints(rh);
            if(i==1){
                g1d.drawString("HAFTANIN", c/2+a-60, d/2+b);
                b+=20;
                g1d.drawString("GÜNLERİ", c/2+a-60, d/2+b);
                b-=20;
            }
            else
                g1d.drawString(siniflar[i], c/2+a-60, d/2+b);
            a+=180;
            if(i%2==1){
                a=100;
                b+=100;
            }
        }
        g2d.dispose();
        g1d.dispose();
    }
    @Override
    public void paintComponent(Graphics g) {
        cizimYap(g);
    }
    class MouseTetikleyici extends MouseAdapter {
        Area a1;
        MouseTetikleyici(Area a1){
            this.a1=a1;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            if (a1.contains(e.getX(), e.getY())) {
                switch(dikdortgen.indexOf(a1)){
                    case 0:
                        new Dersler();
                        break;
                    case 1:
                        new HaftaninGunleri();
                        break;
                    case 2:
                        new Harfler();
                        break;
                    case 3:
                        new Isimler();
                        break;
                    case 4:
                        new Konusmalar();
                        break;
                    case 5:
                        new OYUN();
                        break;
                }
            }
        }
    }
}

public class ANA_SINIF extends JFrame {
    public String path="C:/Users/ASHBAR/IdeaProjects/IsaretDiliOdev/src/images/";
    public static void main(String[] a){
        ANA_SINIF h=new ANA_SINIF();
        h.add(new Kalemm());
        h.setTitle("ANA SAYFA");
        h.setVisible(true);
        h.setLocation(80,50);
        h.setSize(550,550);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public String getPath(){
        return path;
    }
}
