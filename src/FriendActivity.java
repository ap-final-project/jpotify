import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class FriendActivity extends Panel implements addFriend,ServerUpdate{
    //addfriendo set nakardam ba dokme seda mishe

    User user;
    ArrayList<FriendGUI> friendGUIS=new ArrayList<>();
    public FriendActivity(User user) {
        super(3);
        this.user=user;
        Panel main=new Panel(3);
        main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        this.add(main,BorderLayout.CENTER);
    }

    @Override
    public void addFriend(String IP) throws IOException {
        Friend friend=new Friend(IP);
        FriendGUI friendGUI=new FriendGUI(friend);
        friendGUIS.add(friendGUI);
        user.addFriend(IP,friend);
    }

    @Override
    public void otherUsersSongChanged(String IP, String title, String artist) {
        for (FriendGUI friendGui: friendGUIS   ) {
            if (friendGui.getFriend().getIP().equals(IP)){
                Friend friend=friendGui.getFriend();
                friend.setTitle(title);
                friend.setArtist(artist);
                friendGui.changeInfo();
            }
        }
    }
}
