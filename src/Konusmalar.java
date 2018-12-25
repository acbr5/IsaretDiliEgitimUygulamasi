import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

class Yuzeyy extends JPanel{
    private Image img;
    private String path=new ANA_SINIF().getPath();
    private int sayac=0;
    private int a=20, b=20, c=130,d=60;
    private String[] konusmalar={"ANLADIN MI?","ANLAMADIM","BEKLE","BİR DAKİKA","ÇOK ZOR",
            "İYİYİM","LÜTFEN", "MAŞALLAH", "MERHABA", "NASILSIN", "ÖZÜR DİLERİM", "TEŞEKKÜR EDERİM"};
    private ArrayList<Area> dikdortgen;
    Yuzeyy(){
        img= Toolkit.getDefaultToolkit().createImage(path+"ANLADIN_MI.gif");
        dikdortgen=new ArrayList<>();
    }
    private void cizimYap(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        Graphics2D g1d=(Graphics2D) g;

        Font font=new Font("SansSerif", Font.PLAIN, 18);
        for(int i=0; i<konusmalar.length; i++){
            Area a1=new Area(new Rectangle2D.Double(a, b, c, d));
            g2d.setColor(new Color(0,0,0));
            if(sayac==0){
                g2d.fill(a1);
                dikdortgen.add(a1);
            }

            addMouseListener(new MouseTetikleyici(a1));

            if(img!=null){
                g.drawImage(img, 480, 20, 500, 500, this);
            }

            g1d.setColor(new Color(250,60,100));
            g1d.setFont(font);
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            g2d.setRenderingHints(rh);
            if(sayac==0){
                if(i==11){
                    g1d.drawString("TEŞEKKÜR", c/2+a-60, d/2+b);
                    b+=20;
                    g1d.drawString("EDERİM", c/2+a-60, d/2+b);
                }
                else
                    g1d.drawString(konusmalar[i], c/2+a-60, d/2+b);
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
                        img= Toolkit.getDefaultToolkit().createImage(path+"ANLADIN_MI.gif");
                        break;
                    case 1:
                        img= Toolkit.getDefaultToolkit().createImage(path+"ANLAMADIM.gif");
                        break;
                    case 2:
                        img= Toolkit.getDefaultToolkit().createImage(path+"BEKLE.gif");
                        break;
                    case 3:
                        img= Toolkit.getDefaultToolkit().createImage(path+"BIR_DAKIKA.gif");
                        break;
                    case 4:
                        img= Toolkit.getDefaultToolkit().createImage(path+"COK_ZOR.gif");
                        break;
                    case 5:
                        img= Toolkit.getDefaultToolkit().createImage(path+"IYIYIM.gif");
                        break;
                    case 6:
                        img= Toolkit.getDefaultToolkit().createImage(path+"LUTFEN.gif");
                        break;
                    case 7:
                        img= Toolkit.getDefaultToolkit().createImage(path+"MASALLAH.gif");
                        break;
                    case 8:
                        img= Toolkit.getDefaultToolkit().createImage(path+"MERHABA.gif");
                        break;
                    case 9:
                        img= Toolkit.getDefaultToolkit().createImage(path+"NASILSIN.gif");
                        break;
                    case 10:
                        img= Toolkit.getDefaultToolkit().createImage(path+"OZUR_DILERIM.gif");
                        break;
                    case 11:
                        img= Toolkit.getDefaultToolkit().createImage(path+"TESEKKUR_EDERIM.gif");
                        break;
                }
            }
        }
    }
}

public class Konusmalar extends JFrame{
    Konusmalar(){
        JFrame jf=new JFrame();
        jf.add(new Yuzeyy());
        jf.setVisible(true);
        jf.setTitle("KONUŞMALAR");
        jf.setLocation(60,15);
        jf.setSize(1200,750);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] a){
        new Konusmalar();
    }
}