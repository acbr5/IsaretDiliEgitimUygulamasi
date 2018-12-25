import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.*;
import java.util.Random;
import java.awt.geom.Area;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.Timer;
import java.awt.geom.Rectangle2D;

class Panell extends JPanel implements ActionListener{
    private Image img;
    private Image soruResim;
    private ImageIcon image;
    private String path=new ANA_SINIF().getPath();
    private Araba araba;
    private Timer zamanlayici;
    private Area cevap1;
    private Area cevap2;
    private Area cevap3;
    private Area cevap4;
    private ArrayList<Area> şıklar;
    private boolean cevap=true;
    private int GECIKME=25;
    private int gunesX=40;
    private int gunesY=60;
    private int count=0;
    public int m=0;
    private int milisaniye=00;
    private int saniye=00;
    private int skor=0;
    private String[] kelimeler={"AY","DAKIKA","DOGRU","O","CUMA","ALMANCA", "CARSAMBA","ANLAMADIM",
            "D", "DIN_KULTURU","BEKLE", "L" ,"YANLIS", "BIR_DAKIKA", "ANLADIN_MI", "K",
            "YAS", "COK_ZOR", "B", "CUMARTESI"};
    private String[] ekResimler={"sun.png","House.png","boy 1.png","Car2.png","dog.png","Frankenstein_WalkCycle.png","House",
            "OL foliage 01.png","Palm Tree1.png","Palm Tree2.png","SkyMountainsLake.png","tree 01.png",
            "tree 02.png","Tree3.png","William Walk.png","Tree1.png","Li-Lee Walk.png","Tree2.png","Clouds1.png",
            "Garbage.png","City.png","car3.png","OL foliage 02.png"};
    Panell(){
        image=new ImageIcon(path+ekResimler[21]);
        img=image.getImage();
        soruResim=Toolkit.getDefaultToolkit().createImage(path+kelimeler[m]+".gif");
        addKeyListener(new KlavyeDinleyicisi());
        setFocusable(true);
        setBackground(Color.WHITE);

        araba=new Araba();

        zamanlayici=new Timer(GECIKME,this);
        zamanlayici.start();
    }
    private void cizimYap(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        Graphics2D g2=(Graphics2D) g;
        //arabanın sayfanın başına alınması
        if(araba.getX()==1150 || araba.getX()==1185){
            araba.x=0;
            araba.y=400;
            count++;
            m+=2;
            soruResim=Toolkit.getDefaultToolkit().createImage(path+kelimeler[m]+".gif");
        }
        //güneşin sayfanın başına alınması
        if(gunesX==1150 || gunesY==1185){
            gunesX=0;
            gunesY=60;
        }
        //yol çizimi
        int sayac=150;
        g2d.drawLine(0, araba.getY()-1, this.getWidth(), araba.getY()-1);
        g2d.drawLine(0, araba.getY()+95, this.getWidth(), araba.getY()+95);
        for(int i=0; i<10; i++){
            if(i==0)
                g2d.drawLine(0, araba.getY()+50, sayac-100, araba.getY()+50);
            g2d.drawLine(sayac-50, araba.getY()+50, sayac, araba.getY()+50);
            sayac+=120;
        }

        //araba çizimi
        g2d.drawImage(img, araba.getX(), araba.getY(),95,95,this);

        //bulutların çizimi
        int j=0;
        for(int i=0; i<30; i++){
            g2d.drawImage(new ImageIcon(path+ekResimler[18]).getImage(), j, 68,this);
            g2d.drawImage(new ImageIcon(path+ekResimler[18]).getImage(), j, 0,this);
            j+=50;
        }

        //güneş çizimi
        g2d.drawImage(new ImageIcon(path+ekResimler[0]).getImage(), gunesX, gunesY,this);

        //çiçeklerin çizimi
        int k=0;
        for(int i=0; i<30; i++){
            g2d.drawImage(new ImageIcon(path+ekResimler[7]).getImage(), k, 500,this);
            k+=50;
        }
            k=0;
            //ağaç çizimi
            for(int i=0; i<20; i++){
                g2d.drawImage(new ImageIcon(path+ekResimler[17]).getImage(), k, 270,100,100,this);
                g2d.drawImage(new ImageIcon(path+ekResimler[17]).getImage(), 87, 270,100,100,this);
                k+=85;
        }

        //Sorular
        if(count%2==0 && m!=8) {
            g2d.drawImage(soruResim, 480, 80, 350, 350, this);
            cevap1 = new Area(new Rectangle2D.Double(476, 425, 89, 60));
            cevap2 = new Area(new Rectangle2D.Double(567, 425, 89, 60));
            cevap3 = new Area(new Rectangle2D.Double(660, 425, 89, 60));
            cevap4 = new Area(new Rectangle2D.Double(750, 425, 89, 60));
            //yazı büyüklüğü ve şıkların yazılması
            Font font=new Font("SansSerif", Font.PLAIN, 17);
            g2.setFont(font);
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHints(rh);
            g2d.drawString("OYUNA BAŞLAMAK İÇİN DOĞRU CEVABA TIKLAMALISINIZ", 425,630);
            g2d.drawString("ARABANIN HAREKET ETMESİ İÇİN SPACE TUŞUNA BASINIZ.", 425,660);
            şıklar = new ArrayList<>();
            g2d.fill(cevap1);
            şıklar.add(cevap1);
            g2d.fill(cevap2);
            şıklar.add(cevap2);
            g2d.fill(cevap3);
            şıklar.add(cevap3);
            g2d.fill(cevap4);
            şıklar.add(cevap4);
            g2d.setPaint(new Color(65,145,152));
            if(m==0){
                g2d.drawString(kelimeler[m],485,460);
                g2d.drawString(kelimeler[m+6],567,460);
                g2d.drawString(kelimeler[m+2],659,460);
                g2d.drawString(kelimeler[m+4],750,460);
            }
            if(m==2){
                g2d.drawString(kelimeler[m+2],485,460);
                g2d.drawString(kelimeler[m+6],567,460);
                g2d.drawString(kelimeler[m],659,460);
                g2d.drawString(kelimeler[m+4],750,460);
            }
            if(m==4){
                g2d.drawString(kelimeler[m+2],475,460);
                g2d.drawString(kelimeler[m+6],567,460);
                g2d.drawString(kelimeler[m+4],660,460);
                g2d.drawString(kelimeler[m],750,460);
            }
            if(m==6){
                g2d.drawString(kelimeler[m+2],480,460);
                g2d.drawString(kelimeler[m],565,460);
                g2d.drawString(kelimeler[m+6],659,460);
                g2d.drawString(kelimeler[m+4],750,460);
            }
            addMouseListener(new MouseTetikleyici(cevap1, cevap2, cevap3, cevap4));
        }
        //zaman ve skorun yazılması
        Font font1=new Font("SansSerif", Font.PLAIN, 20);
        g2d.setFont(font1);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.setColor(new Color(125,10,175));
        g2d.drawString("ZAMAN: "+saniye+":"+milisaniye, 950,175);
        g2d.drawString("SKOR  : "+skor, 950,215);
        Font font2=new Font("SansSerif", Font.PLAIN, 18);
        g2d.setFont(font2);
        g2d.drawString("OYUN DÖRT SORUDAN OLUŞMAKTADIR.", 837,255);
        milisaniye++;
        if(milisaniye==100){
            saniye++;
            milisaniye=0;
        }

        if(m==8){
            Font font=new Font("SansSerif", Font.PLAIN, 20);
            g2d.setFont(font);
            RenderingHints rh1 = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHints(rh1);
            g2d.setColor(new Color(125,10,175));
            g2d.drawString("OYUNU KAZANDINIZ. TEBRİKLER :)", 425,630);
        }
        g2d.dispose();
    }
    @Override
    public void paintComponent(Graphics g) {
        if(m!=8){
            super.paintComponent(g);
            cizimYap(g);
            Toolkit.getDefaultToolkit().sync();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(count%2 ==0)
            araba.durdur();
        else{
            araba.hareketEttir();
        }
        repaint();
    }

    class MouseTetikleyici extends MouseAdapter {
        Area a1;
        MouseTetikleyici(Area a1, Area a2, Area a3, Area a4){
            if(m==0)
                this.a1=a1;
            else if(m==2)
                this.a1=a3;
            else if(m==4)
                this.a1=a4;
            else if(m==6)
                this.a1=a2;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            if (a1.contains(e.getX(), e.getY())) {
                if(m==0){
                    switch(şıklar.indexOf(a1)){
                        case 0:
                            cevap=true;
                            count++;
                            skor++;
                            break;
                        case 1:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                        case 2:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                        case 3:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                    }
                }
                if(m==2){
                    switch(şıklar.indexOf(a1)){
                        case 0:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                        case 1:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                        case 2:
                            cevap=true;
                            count++;
                            skor++;
                            break;
                        case 3:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                    }
                }
                if(m==4){
                    switch(şıklar.indexOf(a1)){
                        case 0:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                        case 1:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                        case 2:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                        case 3:
                            cevap=true;
                            count++;
                            skor++;
                            break;
                    }
                }
                if(m==6){
                    switch(şıklar.indexOf(a1)){
                        case 0:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                        case 1:
                            cevap=true;
                            count++;
                            skor++;
                            break;
                        case 2:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                        case 3:
                            cevap=false;
                            count++;
                            skor--;
                            break;
                    }
                }
            }
        }
    }
    private class KlavyeDinleyicisi extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(cevap==true){
                araba.tusBasildi(e);
            }
            //güneşin hareket etmesi için
            gunesX+=1;
        }
        @Override
        public void keyReleased(KeyEvent e) {
            gunesX+=0;
            if(cevap==false)
                araba.tusBirakildi(e);
        }
    }
}

public class OYUN extends JFrame{
    OYUN(){
        JFrame jf=new JFrame();
        jf.add(new Panell());
        jf.setVisible(true);
        jf.setTitle("OYUN");
        jf.setLocation(60,15);
        jf.setSize(1200,750);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] a){
        new OYUN();
    }
}