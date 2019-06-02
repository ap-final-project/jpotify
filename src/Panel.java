import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    Color dark=new Color(24,24,24);
    Color bright=new Color(194,194,194);

    public Panel(LayoutManager layout, boolean isDoubleBuffered) {

        super(layout, isDoubleBuffered);
        this.setOpaque(true);
        this.setBackground(dark);
        this.setForeground(bright);
    }

    public Panel(LayoutManager layout) {
        super(layout);
        this.setOpaque(true);
        this.setBackground(dark);
        this.setForeground(bright);
    }

    public Panel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        this.setOpaque(true);
        this.setBackground(dark);
        this.setForeground(bright);
    }

    public Panel() {
        this.setOpaque(true);
        this.setBackground(dark);
        this.setForeground(bright);
    }
}
