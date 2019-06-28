import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class FriendGUI extends Panel{
    private Label pic;
    private Label info;
    private Label status;
    private Friend friend;
    private InformSocket informSocket;
    public FriendGUI(Friend friend) {
        super(1);
        this.friend=friend;
        pic=new Label(1);
        info=new Label(1);
        status=new Label(1);
        status.setIcon(new ImageIcon("img\\download.png"));
        pic.setIcon(new ImageIcon(friend.getImg()));
        info.setText("<html>"+friend.getName()+"<br>"+friend.getTitle()+"<br>"+friend.getArtist()+"</html>");
        this.setLayout(new BorderLayout());
        this.add(pic,BorderLayout.WEST);
        this.add(info,BorderLayout.CENTER);
        this.add(status,BorderLayout.EAST);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    informSocket.getSong(friend.getIP());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        this.status.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                informSocket.getPlaylist(friend.getIP());
            }
        });
    }

    public void setInformSocket(InformSocket informSocket) {
        this.informSocket = informSocket;
    }

    public Friend getFriend() {
        return friend;
    }

    public void changeInfo() {
        info.setText("<html>"+friend.getName()+"<br>"+friend.getTitle()+"<br>"+friend.getArtist()+"</html>");
    }
    public void setImage(byte[] img){
        pic.setIcon(new ImageIcon(img));
    }
}
