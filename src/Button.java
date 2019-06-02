import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    Color dark=new Color(24,24,24);
    public Button() {
        this.setOpaque(true);
        this.setBackground(dark);
    }

    public Button(Icon icon) {
        super(icon);
        this.setOpaque(true);
        this.setBackground(dark);
    }

    public Button(String text) {
        super(text);
        this.setOpaque(true);
        this.setBackground(dark);
    }

    public Button(Action a) {
        super(a);
        this.setOpaque(true);
        this.setBackground(dark);
    }

    public Button(String text, Icon icon) {
        super(text, icon);
        this.setOpaque(true);
        this.setBackground(dark);
    }
}
