import javax.swing.*;

public class FriendActivity extends Panel{
    public FriendActivity() {
        super(3);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        FriendGUI f=new FriendGUI();
        FriendGUI f1=new FriendGUI();
        FriendGUI f2=new FriendGUI();
        this.add(f1);
        this.add(f2);
        this.add(f);
    }
}
