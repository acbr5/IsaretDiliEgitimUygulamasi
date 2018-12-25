import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.*;
import java.awt.geom.Area;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.Rectangle2D;

class Surface extends JPanel{
    private Image img;
    private String path=new ANA_SINIF().getPath();
    private int sayac=0;
    private int a=20, b=20, c=80,d=60;
    private String[] harfler={"A","B","C","Ç","D","E","F","G","Ğ","H","I","İ","J",
            "K","L","M","N","O","Ö","P","R","S","Ş","T","U","Ü","V","Y","Z"};
    private ArrayList<Area> dikdortgen;
    Surface(){
        img= Toolkit.getDefaultToolkit().createImage(path+"A.gif");
        dikdortgen=new ArrayList<>();
    }
    private void cizimYap(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        Graphics2D g1d=(Graphics2D) g;

        Font font=new Font("SansSerif", Font.PLAIN, 18);
        for(int i=0; i<harfler.length; i++){
            Area a1=new Area(new Rectangle2D.Double(a, b, c, d));
            g2d.setColor(new Color(65,155,241));
            if(sayac==0){
                g2d.fill(a1);
                dikdortgen.add(a1);
            }

            addMouseListener(new MouseTetikleyici(a1));

            if(img!=null){
                g.drawImage(img, 480, 20, 500, 500, this);
            }

            g1d.setColor(new Color(150,75,210));
            g1d.setFont(font);
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            g2d.setRenderingHints(rh);
            if(sayac==0){
                g1d.drawString(harfler[i], c/2+a-5, d/2+b);
                a+=100;
                if(i==3 || i==7 || i==11 || i==15 || i==19 || i==23){
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
                        img= Toolkit.getDefaultToolkit().createImage(path+"A.gif");
                        break;
                    case 1:
                        img= Toolkit.getDefaultToolkit().createImage(path+"B.gif");
                        break;
                    case 2:
                        img= Toolkit.getDefaultToolkit().createImage(path+"C.gif");
                        break;
                    case 3:
                        img= Toolkit.getDefaultToolkit().createImage(path+"Ç.gif");
                        break;
                    case 4:
                        img= Toolkit.getDefaultToolkit().createImage(path+"D.gif");
                        break;
                    case 5:
                        img= Toolkit.getDefaultToolkit().createImage(path+"E.gif");
                        break;
                    case 6:
                        img= Toolkit.getDefaultToolkit().createImage(path+"F.gif");
                        break;
                    case 7:
                        img= Toolkit.getDefaultToolkit().createImage(path+"G.gif");
                        break;
                    case 8:
                        img= Toolkit.getDefaultToolkit().createImage(path+"Ğ.gif");
                        break;
                    case 9:
                        img= Toolkit.getDefaultToolkit().createImage(path+"H.gif");
                        break;
                    case 10:
                        img= Toolkit.getDefaultToolkit().createImage(path+"I.gif");
                        break;
                    case 11:
                        img= Toolkit.getDefaultToolkit().createImage(path+"İ.gif");
                        break;
                    case 12:
                        img= Toolkit.getDefaultToolkit().createImage(path+"J.gif");
                        break;
                    case 13:
                        img= Toolkit.getDefaultToolkit().createImage(path+"K.gif");
                        break;
                    case 14:
                        img= Toolkit.getDefaultToolkit().createImage(path+"L.gif");
                        break;
                    case 15:
                        img= Toolkit.getDefaultToolkit().createImage(path+"M.gif");
                        break;
                    case 16:
                        img= Toolkit.getDefaultToolkit().createImage(path+"N.gif");
                        break;
                    case 17:
                        img= Toolkit.getDefaultToolkit().createImage(path+"O.gif");
                        break;
                    case 18:
                        img= Toolkit.getDefaultToolkit().createImage(path+"Ö.gif");
                        break;
                    case 19:
                        img= Toolkit.getDefaultToolkit().createImage(path+"P.gif");
                        break;
                    case 20:
                        img= Toolkit.getDefaultToolkit().createImage(path+"R.gif");
                        break;
                    case 21:
                        img= Toolkit.getDefaultToolkit().createImage(path+"S.gif");
                        break;
                    case 22:
                        img= Toolkit.getDefaultToolkit().createImage(path+"Ş.gif");
                        break;
                    case 23:
                        img= Toolkit.getDefaultToolkit().createImage(path+"T.gif");
                        break;
                    case 24:
                        img= Toolkit.getDefaultToolkit().createImage(path+"U.gif");
                        break;
                    case 25:
                        img= Toolkit.getDefaultToolkit().createImage(path+"Ü.gif");
                        break;
                    case 26:
                        img= Toolkit.getDefaultToolkit().createImage(path+"V.gif");
                        break;
                    case 27:
                        img= Toolkit.getDefaultToolkit().createImage(path+"Y.gif");
                        break;
                    case 28:
                        img= Toolkit.getDefaultToolkit().createImage(path+"Z.gif");
                        break;
                }
            }
        }
    }
}

public class Harfler extends JFrame{
    Harfler(){
        JFrame jf=new JFrame();
        jf.add(new Surface());
        jf.setVisible(true);
        jf.setTitle("HARFLER");
        jf.setLocation(60,15);
        jf.setSize(1200,750);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] a){
        new Harfler();
    }
}