import javax.swing.*;
import java.awt.*;

public class UserGUI extends Panel {
    Label pic=new Label(2);
    Label userName=new Label(2);
    public UserGUI() {
        super(3);
        Image img=Toolkit.getDefaultToolkit().getImage("img\\favoriteCover.png");
        img=img.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        pic.setIcon(new ImageIcon(img));
        userName.setText("kian");
        this.setLayout(new GridLayout(1,2));
        this.add(userName);
        this.add(pic);
    }
}
