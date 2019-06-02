import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Button extends JButton {
    Color dark1=new Color(24,24,24);
    Color dark2=new Color(18,18,18);
    Color dark3=new Color(40,40,40);
    Color bright=new Color(194,194,194);
    public Button(int c) {
        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            default: color=dark1;
        }
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setOpaque(true);
        this.setBackground(color);
        this.setForeground(bright);
    }

    public Button(Icon icon,int c) {

        super(icon);

        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            default: color=dark1;
        }
        this.setOpaque(true);
        this.setBackground(color);
        this.setForeground(bright);

    }

    public Button(String text,int c) {
        super(text);

        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            default: color=dark1;
        }
        this.setOpaque(true);
        this.setBackground(color);
        this.setForeground(bright);

    }

    public Button(Action a,int c) {
        super(a);

        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            default: color=dark1;
        }
        this.setOpaque(true);
        this.setBackground(color);
        this.setForeground(bright);

    }

    public Button(String text, Icon icon,int c) {
        super(text, icon);

        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            default: color=dark1;
        }
        this.setOpaque(true);
        this.setForeground(bright);
        this.setBackground(color);
    }
}
