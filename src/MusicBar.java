import javax.swing.*;
import javax.swing.plaf.TextUI;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class MusicBar extends JPanel {
    JButton play;
    JButton pause;
    JButton stop;
    JButton next;
    JButton previous;
    JLabel songName=new JLabel("songName");
    JLabel artistName=new JLabel("ArtistName");
    JLabel album=new JLabel("AlbumName");
    JProgressBar progressBar;
    public MusicBar() {
        this.setLayout(new BorderLayout());
        JPanel info=new JPanel();
        info.setLayout(new GridLayout(3,1));
        info.add(songName);
        info.add(artistName);
        info.add(album);
        JPanel bottom=new JPanel();
        bottom.setLayout(new GridLayout(1,5));
        progressBar=new JProgressBar();
        progressBar.setValue(54);
        progressBar.setStringPainted(false);
        progressBar.setPreferredSize(new Dimension(300,10));
        JPanel top=new JPanel();
        top.add(progressBar);
        this.add(info,BorderLayout.WEST);
        play = new JButton();
        pause = new JButton();
        stop = new JButton();
        next = new JButton();
        previous = new JButton();
        play.setBorder(BorderFactory.createEmptyBorder());
        pause.setBorder(BorderFactory.createEmptyBorder());
        previous.setBorder(BorderFactory.createEmptyBorder());
        next.setBorder(BorderFactory.createEmptyBorder());
        stop.setBorder(BorderFactory.createEmptyBorder());
        play.setIcon(new ImageIcon("play.png"));
        pause.setIcon(new ImageIcon("pause.png"));
        stop.setIcon(new ImageIcon("stop.png"));
        previous.setIcon(new ImageIcon("back.png"));
        next.setIcon(new ImageIcon("forward.png"));
        bottom.add(previous);
        bottom.add(pause);
        bottom.add(play);
        bottom.add(stop);
        bottom.add(next);
        JPanel keke=new JPanel();
        keke.setLayout(new GridLayout(2,1));
        keke.add(top);
        keke.add(bottom);
        this.add(keke,BorderLayout.EAST);
        this.add(info,BorderLayout.WEST);
    }
}
