import javax.swing.*;
import java.awt.*;

public class UserGUI extends Panel {
    Label pic=new Label(3);
    Label userName=new Label(3);
    public UserGUI() {
        super(3);
        Image img=Toolkit.getDefaultToolkit().getImage("img\\user1.png");
        img=img.getScaledInstance(45,45,Image.SCALE_SMOOTH);
        pic.setIcon(new ImageIcon(img));
        pic.setBorder(BorderFactory.createEmptyBorder(12,0,2,12));
        userName.setText("kian");
        this.setLayout(new GridLayout(1,2));
        this.add(userName);
        this.add(pic);
    }
}
