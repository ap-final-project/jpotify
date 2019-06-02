import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    Color dark =new Color(24,24,24);
    public Label(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        this.setOpaque(true);
        this.setBackground(dark);
    }

    public Label(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        this.setOpaque(true);
        this.setBackground(dark);
    }

    public Label(String text) {
        super(text);
        this.setOpaque(true);
        this.setBackground(dark);
    }

    public Label(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        this.setOpaque(true);
        this.setBackground(dark);
    }

    public Label(Icon image) {
        super(image);
        this.setOpaque(true);
        this.setBackground(dark);
    }

    public Label() {
        this.setOpaque(true);
        this.setBackground(dark);
    }
}
