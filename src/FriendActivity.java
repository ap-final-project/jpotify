import javax.swing.*;
import java.awt.*;

public class FriendActivity extends Panel{
    public FriendActivity() {
        super(3);
        Panel main=new Panel(3);
        main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        FriendGUI f=new FriendGUI();
        FriendGUI f1=new FriendGUI();
        FriendGUI f2=new FriendGUI();
        main.add(f1);
        main.add(f2);
        main.add(f);
        this.add(main,BorderLayout.CENTER);
        Label pro=new Label(3);
        pro.setText("<html>"+"kian");

    }
}
