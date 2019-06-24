import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PLGUI extends Panel {
    Label label;
    Label pic;
    Playlist playlist;

    public Playlist getPlaylist() {
        return playlist;
    }

    public PLGUI(Playlist playlist, String songName, String path){
        super(3);
        this.playlist=playlist;
        pic=new Label(3);
        Image image=Toolkit.getDefaultToolkit().getImage(path);
        Image newimg = image.getScaledInstance(150, 150,  Image.SCALE_SMOOTH);
        ImageIcon imageIcon=new ImageIcon(newimg);
        pic.setIcon(imageIcon);
        label=new Label("<html>"+playlist.name+"<br>"+playlist.description+"<br>"+playlist.songs.size()+"songs"+"</html>",3);
        this.setLayout(new BorderLayout());
        pic.setSize(200,200);
        pic.setSize(200,200);
        this.add(pic,BorderLayout.CENTER);
        this.add(label,BorderLayout.PAGE_END);
    }
}
