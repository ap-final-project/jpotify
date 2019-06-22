import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PLGUI extends Panel {
    Label name;
    Label nums;
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
        Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon=new ImageIcon(newimg);
        pic.setIcon(imageIcon);
        name=new Label(songName,3);
        nums=new Label("23 song",3);
        this.setLayout(new BorderLayout());
        Panel panel=new Panel(3);
        Panel details=new Panel(3);
        details.setLayout(new GridLayout(1,2));
        pic.setSize(200,200);
        details.add(name);
        details.add(nums);
        panel.setLayout(new GridLayout(2,1));
        Panel pp=new Panel(3);
        pp.add(pic);
        panel.add(pp);
        pic.setSize(200,200);
        panel.add(details);
        this.add(panel,BorderLayout.CENTER);
    }
}
