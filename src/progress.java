
// Java Program to create a 
// simple progress bar 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class progress extends Panel {
    private JProgressBar b;
    Label currentTime=new Label(3);
public progress(){
    super(3);
    this.setLayout(new FlowLayout());
    this.add(currentTime);
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
    public void setTime(int time){
        String lenght="";
        if(time%60>9)
            lenght=""+time/60+":"+time%60;
        else if(time%60<10 && time%60!=0)
            lenght=""+time/60+":"+0+time%60;
        else
            lenght=""+time/60+":"+00+time%60;
        currentTime.setText(lenght);
    }
}