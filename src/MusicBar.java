
import jaco.mp3.player.MP3Player;

import java.awt.*;
import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.*;

public class MusicBar extends JPanel implements AddPlaylistListener{
    MP3Player player = new MP3Player();
    public MusicBar(){
        player.setRepeat(true);
//        player.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
//        this.setOpaque(true);
        this.add(player);
        Auid
        this.setBackground(Color.DARK_GRAY);
//        this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
//        this.setOpaque(true);
        setVisible(true);
    }

    @Override
    public void addToPlayList(File file) {
        player.addToPlayList(file);
    }
}
