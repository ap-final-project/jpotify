import javax.swing.*;
import java.awt.*;

public class UserGUI extends Panel {
    Label pic=new Label(3);
    Label userName=new Label(3);
    User user;
    public UserGUI(User user) {
        super(3);
        this.user=user;
        Image img=Toolkit.getDefaultToolkit().getImage(user.getImgPath());
        img=img.getScaledInstance(45,45,Image.SCALE_SMOOTH);
        userName.setText(user.getName());
        pic.setIcon(new ImageIcon(img));
        pic.setBorder(BorderFactory.createEmptyBorder(12,0,2,12));
        this.setLayout(new GridLayout(1,2));
        this.add(userName);
        this.add(pic);
    }
}
