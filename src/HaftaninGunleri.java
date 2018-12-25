import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.*;
import java.awt.geom.Area;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.Rectangle2D;

class Kalem extends JPanel{
    private Image img;
    private String path=new ANA_SINIF().getPath();
    private int sayac=0;
    private int a=20, b=20, c=130,d=60;
    private String[] gunler={"PAZARTESİ","SALI","ÇARŞAMBA","PERŞEMBE","CUMA",
            "CUMARTESİ","PAZAR"};
    private ArrayList<Area> dikdortgen;
    Kalem(){
        img= Toolkit.getDefaultToolkit().createImage(path+"PAZARTESI.gif");
        dikdortgen=new ArrayList<>();
    }
    private void cizimYap(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        Graphics2D g1d=(Graphics2D) g;

        Font font=new Font("SansSerif", Font.PLAIN, 18);
        for(int i=0; i<gunler.length; i++){
            Area a1=new Area(new Rectangle2D.Double(a, b, c, d));
            g2d.setColor(new Color(200,160,100));
            if(sayac==0){
                g2d.fill(a1);
                dikdortgen.add(a1);
            }

            addMouseListener(new MouseTetikleyici(a1));

            if(img!=null){
                g.drawImage(img, 480, 20, 500, 500, this);
            }

            g1d.setColor(new Color(240,50,50));
            g1d.setFont(font);
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            g2d.setRenderingHints(rh);
            if(sayac==0){
                g1d.drawString(gunler[i], c/2+a-60, d/2+b);
                a+=150;
                if(i%2==1){
                    a=20;
                    b+=100;
                }
            }
        }
        g2d.dispose();
        g1d.dispose();
        sayac++;
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
                        img= Toolkit.getDefaultToolkit().createImage(path+"PAZARTESI.gif");
                        break;
                    case 1:
                        img= Toolkit.getDefaultToolkit().createImage(path+"SALI.gif");
                        break;
                    case 2:
                        img= Toolkit.getDefaultToolkit().createImage(path+"CARSAMBA.gif");
                        break;
                    case 3:
                        img= Toolkit.getDefaultToolkit().createImage(path+"PERSEMBE.gif");
                        break;
                    case 4:
                        img= Toolkit.getDefaultToolkit().createImage(path+"CUMA.gif");
                        break;
                    case 5:
                        img= Toolkit.getDefaultToolkit().createImage(path+"CUMARTESI.gif");
                        break;
                    case 6:
                        img= Toolkit.getDefaultToolkit().createImage(path+"PAZAR.gif");
                        break;
                }
            }
        }
    }
}

public class HaftaninGunleri extends JFrame{
    HaftaninGunleri(){
        JFrame jf=new JFrame();
        jf.add(new Kalem());
        jf.setVisible(true);
        jf.setTitle("HAFTANIN GÜNLERİ");
        jf.setLocation(60,15);
        jf.setSize(1200,750);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] a){
        new HaftaninGunleri();
    }
}