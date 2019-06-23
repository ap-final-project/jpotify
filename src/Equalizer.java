
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Equalizer extends Panel {
    Color c1=new Color(255,30,74);
    Color c3=new Color(46,20,109);
    Color c2=new Color(255,113,98);
    Color c4=new Color(32,18,69);
    ArrayList<Color> colors=new ArrayList<>();
    ArrayList<JProgressBar> barsU=new ArrayList<>();
    ArrayList<JProgressBar> barsD=new ArrayList<>();
    public Equalizer(){
        super(1);
//        this.setPreferredSize(new Dimension(1000,300));
        colors.add(c1);
        colors.add(c2);
        colors.add(c3);
        colors.add(c4);
        for (int i = 0; i <32 ; i++) {
            JProgressBar jpU=new JProgressBar(JProgressBar.VERTICAL,0,10000);
//            jpU.setSize(32,150);
            this.setLayout(new GridLayout(2,32));
            this.add(jpU,i);
            barsU.add(jpU);
            jpU.setForeground(colors.get(i%4));
            jpU.setBackground(new Color(63,63,63));
            jpU.setStringPainted(false);
            jpU.setValue(5000);
            jpU.setBorderPainted(false);
            jpU.setBorder(BorderFactory.createLineBorder(new Color(63,63,63),20));
            jpU.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        }
        for (int i=0;i<32;i++){
            JProgressBar jpD=new JProgressBar(JProgressBar.VERTICAL,0,10000);
//            jpD.setSize(32,150);
            this.add(jpD,i+32);
            barsD.add(jpD);
            jpD.setBackground(colors.get(i%4));
            jpD.setForeground(new Color(63,63,63));
            jpD.setStringPainted(false);
            jpD.setValue(5000);
            jpD.setBorderPainted(false);
            jpD.setBorder(BorderFactory.createLineBorder(new Color(63,63,63),20));
            jpD.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        }
    }
    public void setValues(short[] V){
        for (int i = 0; i <32 ; i++) {
            barsU.get(i).setValue(V[i]/2);
            barsD.get(i).setValue(10000-Math.abs(V[i])/2);
        }
    }
}
