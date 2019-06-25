
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
    Panel upper=new Panel(2);
    Panel lower=new Panel(2);
    public Equalizer(){
        super(2);
        colors.add(c1);
        colors.add(c2);
        colors.add(c3);
        colors.add(c4);
        GridLayout equlizerLayout=new GridLayout(2,1);
        equlizerLayout.setHgap(0);
        this.setLayout(equlizerLayout);
        this.setPreferredSize(new Dimension(1000,300));
//        this.setMaximumSize(new Dimension(1000,50));
//        this.setMinimumSize(new Dimension(1000,50));
        upper.setLayout(new GridLayout(1,64));
        lower.setLayout(new GridLayout(1,64));
        this.add(upper);
        this.add(lower);
        for (int i = 0; i <64 ; i++) {
            JProgressBar jpU=new JProgressBar(JProgressBar.VERTICAL,0,10000);
            Panel panel=new Panel(2);

//            SpringLayout layout=new SpringLayout();
//            panel.setLayout(layout);
//            layout.putConstraint(SpringLayout.SOUTH,jpU);
            upperPanels.add(panel);
            upper.add(panel);
            panel.add(jpU);
            barsU.add(jpU);
//            panel.setBackground(Color.cyan);
            panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            jpU.setForeground(colors.get(i%4));
            jpU.setBackground(new Color(18,18,18));
            jpU.setStringPainted(false);
            jpU.setValue(5000);
            jpU.setBorderPainted(false);
            jpU.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        }
        for (int i=0;i<64;i++){
            JProgressBar jpD=new JProgressBar(JProgressBar.VERTICAL,0,10000);
            Panel panel=new Panel(2);
            panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            lowerPanels.add(panel);
            lower.add(panel);
            panel.add(jpD);
//            panel.setBackground(Color.cyan);

            barsD.add(jpD);
            jpD.setBackground(colors.get(i%4));
            jpD.setForeground(new Color(18,18,18));
            jpD.setStringPainted(false);
            jpD.setValue(5000);
            jpD.setBorderPainted(false);
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
