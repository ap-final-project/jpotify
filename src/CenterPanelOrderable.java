import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class CenterPanelOrderable{
    JPanel panel;
    Box songs;
    int idx = 0;
    public  void makeUI(ArrayList<SongGUI> guis) {
        songs = Box.createVerticalBox();
        DragMouseAdapter dh = new DragMouseAdapter();
        songs.addMouseListener(dh);
        songs.addMouseMotionListener(dh);
        for (JComponent c :guis) {
            songs.add(createToolbarButton(idx++, c));
        }
        JPanel p = new JPanel(new BorderLayout());
        p.add(songs, BorderLayout.NORTH);
        panel=p;
    }

    private static JComponent createToolbarButton(int i, JComponent c) {
        //label ghermeze add mishe
        JLabel l = new JLabel(String.format(" %d", i));
        l.setOpaque(true);
        l.setBackground(Color.BLACK);
        JPanel p = new JPanel(new BorderLayout());
        p.add(l, BorderLayout.WEST);
        p.add(c);
        p.setOpaque(false);
        return p;
    }
    public void add(SongGUI songGUI){
        songs.add(createToolbarButton(idx++, songGUI));
    }
    public JPanel changePlayList(Playlist playlist){
        idx=0;
        songs = Box.createVerticalBox();
        DragMouseAdapter dh = new DragMouseAdapter();
        songs.addMouseListener(dh);
        songs.addMouseMotionListener(dh);
        for (JComponent c :playlist.guis) {
            songs.add(createToolbarButton(idx++, c));
        }
        panel.add(songs, BorderLayout.NORTH);
        return panel;
    }
}

