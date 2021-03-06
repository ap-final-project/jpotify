import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * class for creating GUI of playlist
 */
public class PLGUI extends Panel{
    Label label;
    Label pic;
    Label more;
    Playlist playlist;

    public Playlist getPlaylist() {
        return playlist;
    }

    /**
     *
     * @param playlist
     * @param songName
     * @param path
     */
    public PLGUI(Playlist playlist, String songName, String path) {
        super(3);
        more = new Label(3);
        more.setIcon(new ImageIcon("img\\moreIcon.png"));
        this.playlist = playlist;
        pic = new Label(3);
        Image image = Toolkit.getDefaultToolkit().getImage(path);
        Image newimg = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newimg);
        pic.setIcon(imageIcon);
        label = new Label("<html>" + " " + playlist.name + "<br>" + playlist.description + "<br>" + " " + playlist.songs.size() + "songs" + "</html>", 3);
        this.setPreferredSize(new Dimension(220, 280));
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        layout.putConstraint(SpringLayout.NORTH, pic, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, pic, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, pic, 10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.SOUTH, pic);
        layout.putConstraint(SpringLayout.EAST, more, 1, SpringLayout.EAST, this);
//        layout.putConstraint(SpringLayout.SOUTH,more,1,SpringLayout.SOUTH,this);
        layout.putConstraint(SpringLayout.NORTH, more, 40, SpringLayout.SOUTH, pic);
        this.add(pic);
        this.add(label);
        this.add(more);
    }
}
