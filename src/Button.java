import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Button extends JButton {
    Color dark=new Color(24,24,24);
    Color bright=new Color(194,194,194);
    public Button() {
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setOpaque(true);
        this.setBackground(dark);
        this.setForeground(bright);
    }

    public Button(Icon icon) {
        super(icon);
        this.setOpaque(true);
        this.setBackground(dark);
        this.setForeground(bright);

    }

    public Button(String text) {
        super(text);
        this.setOpaque(true);
        this.setBackground(dark);
        this.setForeground(bright);

    }

    public Button(Action a) {
        super(a);
        this.setOpaque(true);
        this.setBackground(dark);
        this.setForeground(bright);

    }

    public Button(String text, Icon icon) {
        super(text, icon);
        this.setOpaque(true);
        this.setForeground(bright);
        this.setBackground(dark);
    }
}
