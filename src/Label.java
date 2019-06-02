import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {

    Color dark1=new Color(24,24,24);
    Color dark2=new Color(18,18,18);
    Color dark3=new Color(40,40,40);

    public Label(String text, Icon icon, int horizontalAlignment,int c) {
        super(text, icon, horizontalAlignment);
        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            default: color=dark1;
        }
        this.setOpaque(true);
        this.setBackground(color);
    }

    public Label(String text, int horizontalAlignment,int c) {
        super(text, horizontalAlignment);
        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            default: color=dark1;
        }
        this.setOpaque(true);
        this.setBackground(color);
    }

    public Label(String text,int c) {
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
    }

    public Label(Icon image, int horizontalAlignment,int c) {
        super(image, horizontalAlignment);
        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            default: color=dark1;
        }
        this.setOpaque(true);
        this.setBackground(color);
    }

    public Label(Icon image,int c) {
        super(image);
        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            default: color=dark1;
        }
        this.setOpaque(true);
        this.setBackground(color);
    }

    public Label(int c) {
        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            default: color=dark1;
        }
        this.setOpaque(true);
        this.setBackground(color);
    }
}
