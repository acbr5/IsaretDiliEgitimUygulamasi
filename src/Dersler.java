import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.*;
import java.awt.geom.Area;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.Rectangle2D;

class Yuzey extends JPanel{
    private Image img;
    private String path=new ANA_SINIF().getPath();
    private int sayac=0;
    private int a=20, b=20, c=130,d=60;
    private String[] dersler={"ALMANCA","COĞRAFYA","DİN KÜLTÜRÜ","FELSEFE","FRANSIZCA",
            "İNGİLİZCE","KİMYA","MATEMATİK","MÜZİK","PSİKOLOJİ","RESİM","SOSYAL BİLG.",
            "TARİH","TÜRKÇE"};
    private ArrayList<Area> dikdortgen;
    Yuzey(){
        img= Toolkit.getDefaultToolkit().createImage(path+"ALMANCA.gif");
        dikdortgen=new ArrayList<>();
    }
    private void cizimYap(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        Graphics2D g1d=(Graphics2D) g;

        Font font=new Font("SansSerif", Font.PLAIN, 18);
        for(int i=0; i<dersler.length; i++){
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
                g1d.drawString(dersler[i], c/2+a-60, d/2+b);
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
                        img= Toolkit.getDefaultToolkit().createImage(path+"ALMANCA.gif");
                        break;
                    case 1:
                        img= Toolkit.getDefaultToolkit().createImage(path+"COGRAFYA.gif");
                        break;
                    case 2:
                        img= Toolkit.getDefaultToolkit().createImage(path+"DIN_KULTURU.gif");
                        break;
                    case 3:
                        img= Toolkit.getDefaultToolkit().createImage(path+"FELSEFE.gif");
                        break;
                    case 4:
                        img= Toolkit.getDefaultToolkit().createImage(path+"FRANSIZCA.gif");
                        break;
                    case 5:
                        img= Toolkit.getDefaultToolkit().createImage(path+"INGILIZCE.gif");
                        break;
                    case 6:
                        img= Toolkit.getDefaultToolkit().createImage(path+"KIMYA.gif");
                        break;
                    case 7:
                        img= Toolkit.getDefaultToolkit().createImage(path+"MATEMATIK.gif");
                        break;
                    case 8:
                        img= Toolkit.getDefaultToolkit().createImage(path+"MUZIK.gif");
                        break;
                    case 9:
                        img= Toolkit.getDefaultToolkit().createImage(path+"PSIKOLOJI.gif");
                        break;
                    case 10:
                        img= Toolkit.getDefaultToolkit().createImage(path+"RESIM.gif");
                        break;
                    case 11:
                        img= Toolkit.getDefaultToolkit().createImage(path+"SOSYAL_BILGILER.gif");
                        break;
                    case 12:
                        img= Toolkit.getDefaultToolkit().createImage(path+"TARIH.gif");
                        break;
                    case 13:
                        img= Toolkit.getDefaultToolkit().createImage(path+"TURKCE.gif");
                        break;
                }
            }
        }
    }
}

public class Dersler extends JFrame{
    Dersler(){
        JFrame jf=new JFrame();
        jf.add(new Yuzey());
        jf.setVisible(true);
        jf.setTitle("DERSLER");
        jf.setLocation(60,15);
        jf.setSize(1200,750);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] a){
        new Dersler();
    }
}