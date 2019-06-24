import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    Color bright=new Color(194,194,194);
    Color dark1=new Color(24,24,24);
    Color dark2=new Color(18,18,18);
    Color dark3=new Color(40,40,40);

    public Panel(LayoutManager layout, boolean isDoubleBuffered,int c) {

        super(layout, isDoubleBuffered);
        Color color;
        switch (c){
            case 1:color=dark1; break;
            case 2:color=dark2; break;
            case 3:color=dark3; break;
            case 4:color=bright; break;
            default: color=dark1;
        }
        this.setOpaque(true);
        this.setBackground(color);
        this.setForeground(bright);
    }

    public Panel(LayoutManager layout,int c) {
        super(layout);
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

    public Panel(boolean isDoubleBuffered,int c) {
        super(isDoubleBuffered);
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

    public Panel(int c) {
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
}
