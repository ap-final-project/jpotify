
// Java Program to create a 
// simple progress bar 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class progress extends Panel {
    private JProgressBar b;
public progress(){
    super(3);
    b = new JProgressBar();
    this.add(b);
    b.setOpaque(true);
    b.setBackground(new Color(63,63,63));
    b.setForeground(new Color(176,176,176));
    b.setStringPainted(false);
    b.setValue(0);
    b.setBorderPainted(false);
    b.setBorder(BorderFactory.createEmptyBorder());
    b.setAlignmentX(0);
    b.setAlignmentY(0);
    b.setPreferredSize(new Dimension(800,5));
}
    public void reset(){
    b.setValue(0);
    }
    public void setV(int v){
    b.setValue(v);
    }

}