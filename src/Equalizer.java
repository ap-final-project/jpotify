
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
    ArrayList<Panel> upperPanels=new ArrayList<>();
    ArrayList<Panel> lowerPanels=new ArrayList<>();
    Panel upper=new Panel(1);
    Panel lower=new Panel(1);
    public Equalizer(){
        super(2);
        colors.add(c1);
        colors.add(c2);
        colors.add(c3);
        colors.add(c4);
        this.setMaximumSize(new Dimension(800,300));
        this.setPreferredSize(new Dimension(800,300));
        this.setMinimumSize(new Dimension(800,300));
        FlowLayout flowLayout=new FlowLayout();
        flowLayout.setHgap(0);
        lower.setLayout(flowLayout);
        upper.setLayout(flowLayout);
        this.add(upper);
        this.add(lower);
        for (int i = 0; i <64 ; i++) {
            JProgressBar jpU=new JProgressBar(JProgressBar.VERTICAL,0,10000);
//            GridLayout layout=new GridLayout(2,64);
//            layout.setHgap(0);
//            layout.setVgap(0);
//            this.setLayout(layout);
            Panel panel=new Panel(1);
            upperPanels.add(panel);
            upper.add(panel);
//            this.add(panel,i);
//            jpU.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
            panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            panel.add(jpU);
            barsU.add(jpU);
            jpU.setForeground(colors.get(i%4));
            jpU.setBackground(new Color(18,18,18));

            jpU.setStringPainted(false);
            jpU.setValue(5000);
            jpU.setBorderPainted(false);
//            jpU.setBorder(BorderFactory.createLineBorder(new Color(63,63,63),20));
            jpU.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        }
        for (int i=0;i<64;i++){
            JProgressBar jpD=new JProgressBar(JProgressBar.VERTICAL,0,10000);
//            this.add(jpD,i+64);
            Panel panel=new Panel(1);
            panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
//            jpD.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
            lowerPanels.add(panel);
            lower.add(panel);
//            this.add(panel,i+64);
            panel.add(jpD);

            barsD.add(jpD);
            jpD.setBackground(colors.get(i%4));
            jpD.setForeground(new Color(18,18,18));
            jpD.setStringPainted(false);
            jpD.setValue(5000);
            jpD.setBorderPainted(false);
//            jpD.setBorder(BorderFactory.createLineBorder(new Color(63,63,63),20));
            jpD.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        }
    }
    public void setValues(short[] V){
        for (int i = 0; i <64 ; i++) {
            barsU.get(i).setValue(V[i]/2);
            barsD.get(i).setValue(10000-Math.abs(V[i])/2);
        }
    }
}
