import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Gui of the friend activity panel at the right of the screen
 */
public class FriendActivity extends Panel implements addFriend,ServerUpdate{
    Button addF;
    User user;
    Label label;
    Friend newFriend;
    ArrayList<FriendGUI> friendGUIS=new ArrayList<>();
    Panel main;
    public FriendActivity(User user) {
        super(1);
        addF=new Button(1);
        addF.setText("Add friend");
        this.user=user;
        user.getClientSender().setServerUpdate(this);
        user.getClientReceiver().setServerUpdate(this);
        label=new Label(1);
        label.setText("Friend activity");
        label.setBorder(BorderFactory.createEmptyBorder(15,5,5,5));
        addF.setBorder(BorderFactory.createEmptyBorder(5,15,15,5));
        addF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame();
                frame.setVisible(true);
                frame.setSize(100,100);
                frame.setLayout(new BorderLayout());
                Label label1=new Label("Enter your friend's ip",1);
                Button button=new Button("Add to your friends",1);
                JFormattedTextField textField=new JFormattedTextField();
                textField.setPreferredSize(new Dimension(80,40));
                frame.add(label1,BorderLayout.PAGE_START);
                frame.add(button,BorderLayout.PAGE_END);
                frame.add(textField,BorderLayout.CENTER);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            addFriend(textField.getText());
                            frame.setVisible(false);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if(e.getKeyCode()==KeyEvent.VK_ENTER){
                            button.doClick();
                        }
                    }
                });
            }
        });
        main=new Panel(1);
        main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        this.add(label,BorderLayout.PAGE_START);
        this.add(main,BorderLayout.CENTER);
        this.add(addF,BorderLayout.PAGE_END);
    }

    /**
     *
     * @param IP
     * @throws IOException
     */
    @Override
    public void addFriend(String IP) throws IOException {
        newFriend=new Friend(IP);
        user.getFriendsIPs().add(IP);
        user.getFriends().add(newFriend);
        user.getClientSender().addFriend(IP);
    }

    /**
     *
     * @param IP
     * @param title
     * @param artist
     */
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

    /**
     *
     * @param img
     * @param name
     * @param title
     * @param artist
     */
    @Override
    public void addNewFriend(byte[] img, String name, String title, String artist) {
        newFriend.setImg(img);
        newFriend.setName(name);
        newFriend.setTitle(title);
        newFriend.setArtist(artist);
        FriendGUI friendGUI=new FriendGUI(newFriend);
        friendGUI.setInformSocket(user);
        friendGUIS.add(friendGUI);
        main.add(friendGUI);
    }


}
