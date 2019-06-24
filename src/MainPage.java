import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainPage extends JFrame {
    private final String TITLE = "Kian & Pariya Jpotify";
    LeftBar leftBar = new LeftBar();
    Panel main = new Panel(2);
    MusicBar musicBar = new MusicBar();
    FriendActivity friendActivity = new FriendActivity();
    volatile MusicPlayer musicPlayer = new MusicPlayer();
    CenterSongs centerSongs = new CenterSongs();
    CenterPlayLists centerPlayLists = new CenterPlayLists(musicPlayer.getPlaylists());
    CenterAlbum centerAlbum = new CenterAlbum();
    Center center = new Center(centerSongs, centerPlayLists, musicPlayer.getPlaylists(), centerAlbum);

    public MainPage() throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        this.setTitle(TITLE);
        Color dark = new Color(24, 24, 24);
        this.setBackground(dark);
        this.setForeground(dark);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLayout(new BorderLayout());
        main.add(leftBar, BorderLayout.WEST);
        main.add(musicBar, BorderLayout.PAGE_END);
        main.add(friendActivity,BorderLayout.EAST);
        musicPlayer.setInfoBarListener(musicBar);
        main.add(center, BorderLayout.CENTER);
        leftBar.setaddSongListener(musicPlayer); //addSong
        musicPlayer.setListener(centerSongs); //addGUI
        musicPlayer.setInformArtWrok(leftBar);
        musicBar.setMusicBarListener(musicPlayer);//action
        musicPlayer.setPlayBTNListener(musicBar);//clicked
        musicPlayer.setInformEqualizer(centerSongs);
        leftBar.setCenterTrue(center);
        leftBar.setChoosePlaylist(center);
        centerPlayLists.setChoosePlaylistListener(center);
        centerPlayLists.setMakeVisibilityTrue(center);
        leftBar.setmakePlListener(centerPlayLists);
        musicPlayer.setMakeVisibilityTrue(center);
        centerAlbum.setChoosePlaylist(center);
        centerAlbum.setMakeVisibilityTrue(center);
        this.setVisible(true);
        this.add(main);
        leftBar.setaddSongListener(musicPlayer);
        leftBar.setMakeAlbum(centerAlbum);
        musicBar.setBarUpdateListener(musicPlayer);
        this.setBackground(dark);
    }
}