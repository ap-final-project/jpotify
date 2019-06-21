
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Equalizer extends JPanel {
    Color c1=Color.RED;
    Color c3=Color.green;
    Color c4=Color.pink;
    Color c5=Color.MAGENTA;
    ArrayList<Color> colors=new ArrayList<>();
    ArrayList<JProgressBar> bars=new ArrayList<>();
    public Equalizer(){
        colors.add(c1);
        colors.add(c3);
        colors.add(c4);
        colors.add(c5);
        for (int i = 0; i <32 ; i++) {
            JProgressBar jp=new JProgressBar(JProgressBar.VERTICAL,Short.MIN_VALUE,Short.MAX_VALUE);
            this.setLayout(new GridLayout(1,8));
            this.add(jp);
            bars.add(jp);
            jp.setForeground(colors.get(i%4));
            jp.setBackground(new Color(63,63,63));
            jp.setValue(56);
            jp.setStringPainted(false);
            jp.setBorderPainted(false);
            jp.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),2));
            jp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        }
    }
    public void setValues(short[] V){
        for (int i = 0; i <32 ; i++) {
            bars.get(i).setValue(V[i]);
        }
    }
}
