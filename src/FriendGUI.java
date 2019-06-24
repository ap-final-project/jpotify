import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;

public class FriendGUI extends Panel {
    Label pic;
    Label info;
    Label status;
    public FriendGUI() {
        super(1);
        pic=new Label(1);
        pic.setIcon(new ImageIcon("img\\fullHeart.png"));
        info=new Label(1);
        info.setText("<html>"+"name"+"<br>"+"song name"+"<br>"+"artist"+"</html>");
        status=new Label(1);
        status.setText("online");
        this.setLayout(new BorderLayout());
        this.add(pic,BorderLayout.WEST);
        this.add(info,BorderLayout.CENTER);
        this.add(status,BorderLayout.EAST);
    }
}
