import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
public class MainPage extends JFrame{
    private final String TITLE = "Kian & Pariya Jpotify";
    LeftBar leftBar = new LeftBar();
    Panel main = new Panel(2);
    MusicBar musicBar = new MusicBar();
    volatile MusicPlayer musicPlayer=new MusicPlayer();
    CenterSongs centerSongs=new CenterSongs();
    CenterPlayLists centerPlayLists=new CenterPlayLists();
    Center center=new Center(centerSongs,centerPlayLists);

//    PLGUI plgui=new PLGUI();
    public MainPage() throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        this.setTitle(TITLE);
        Color bright =new Color(194,194,194);
        Color dark=new Color(24,24,24);
        this.setSize(800,800);
        this.setBackground(bright);
        this.setForeground(dark);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLayout(new BorderLayout());
        main.add(leftBar, BorderLayout.WEST);
        main.add(musicBar, BorderLayout.PAGE_END);
        leftBar.setListener(musicPlayer);
        musicPlayer.setInfoBarListener(musicBar);
//        main.add(centerp,BorderLayout.CENTER);
        main.add(center,BorderLayout.CENTER);
//        centerp.setVisible(false);
//        center.setVisible(false);
        musicPlayer.setListener(centerSongs);
        musicPlayer.setAddPlGUI(centerPlayLists);
        musicPlayer.setInformArtWrok(leftBar);
        musicBar.setMusicBarListener(musicPlayer);
        musicPlayer.setPlayBTNListener(musicBar);
        musicPlayer.setInformEqualizer(centerSongs);
        leftBar.setCenterTrue(center);
//        leftBar.setCenterTrue(centerp);
        this.setVisible(true);
        this.add(main);
    }
}