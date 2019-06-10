
// Java Program to create a 
// simple progress bar 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class progress extends Panel {
    static JProgressBar b;
public progress(){
    super(3);
    b = new JProgressBar();
    this.add(b);
    b.setOpaque(true);
    b.setBackground(new Color(63,63,63));
    b.setForeground(new Color(176,176,176));
    b.setStringPainted(false);
    b.setBorderPainted(false);
    b.setBorder(BorderFactory.createEmptyBorder());
    b.setValue(54);
    b.setAlignmentX(0);
    b.setAlignmentY(0);
//    b.setSize(800,5);
        b.setPreferredSize(new Dimension(800,5));
//        b.setMinimumSize(new Dimension(300,5));
}


    // function to increase progress 
    public static void fill()
    {
        int i = 0;
        try {
            while (i <= 100) {
                // fill the menu bar
                b.setValue(i + 10);

                // delay the thread
                Thread.sleep(1000);
                i += 20;
            }
        }
        catch (Exception e) {
        }
    }
}