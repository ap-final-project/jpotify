import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import org.jmusixmatch.MusixMatchException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainPage extends JFrame {
    private final String TITLE = "Kian & Pariya Jpotify";
    LeftBar leftBar = new LeftBar();
    Panel main = new Panel(2);
    MusicBar musicBar;
    FriendActivity friendActivity ;
    volatile MusicPlayer musicPlayer;
    CenterSongs centerSongs ;
    CenterPlayLists centerPlayLists;
    CenterAlbum centerAlbum;
    Center center;
    Top top;

    MakeAlbumListener makeAlbumListener=null;
    User user;
    public MainPage(ArrayList<Playlist> playlists,User user) throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException, MusixMatchException {
        centerAlbum = new CenterAlbum();
        musicPlayer = new MusicPlayer(playlists);
        makeAlbumListener=centerAlbum;
        for (SongGUI gui:playlists.get(0).guis) {
            makeAlbumListener.makeAlbumS(gui.song.album,gui.song,gui);
        }
        this.user=user;
        top=new Top(user);
        musicPlayer.setInformSocket(user);
        CenterPanelOrderable centerPanelOrderable=new CenterPanelOrderable();
        centerSongs = new CenterSongs(centerPanelOrderable);
        this.setTitle(TITLE);
        musicBar = new MusicBar();
        friendActivity = new FriendActivity(user);
        centerPlayLists= new CenterPlayLists(musicPlayer.getPlaylists());
        center = new Center(centerSongs, centerPlayLists, musicPlayer.getPlaylists(), centerAlbum);
        Color dark = new Color(24, 24, 24);
        this.setBackground(dark);
        this.setForeground(dark);
        this.setMinimumSize(new Dimension(1000,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLayout(new BorderLayout());
        main.add(leftBar, BorderLayout.WEST);
        main.add(musicBar, BorderLayout.PAGE_END);
        main.add(friendActivity,BorderLayout.EAST);
        musicPlayer.setInfoBarListener(musicBar);
        main.add(center, BorderLayout.CENTER);
        leftBar.setaddSongListener(musicPlayer); //addSong
        musicPlayer.setListener(centerSongs); //addGUI
        musicPlayer.setPlayBTNListener(musicBar);//clicked
        musicPlayer.setInformArtWrok(leftBar);
        musicBar.setMusicBarListener(musicPlayer);//action
        musicPlayer.setInformEqualizer(centerSongs);
        leftBar.setCenterTrue(center);
        leftBar.setChoosePlaylist(center);
        centerPlayLists.setChoosePlaylistListener(center);
        centerPlayLists.setMakeVisibilityTrue(center);
        leftBar.setmakePlListener(centerPlayLists);
        user.getClientReceiver().setAddSharedPlaylist(centerPlayLists);
        musicPlayer.setMakeVisibilityTrue(center);
        centerAlbum.setChoosePlaylist(center);
        centerAlbum.setMakeVisibilityTrue(center);
        centerPlayLists.setAddSong(musicPlayer);
        this.add(top,BorderLayout.PAGE_START);
        musicPlayer.setSwapToTop(centerPanelOrderable);
        this.setVisible(true);
        this.add(main);
        leftBar.setaddSongListener(musicPlayer);
        leftBar.setMakeAlbum(centerAlbum);
        musicBar.setBarUpdateListener(musicPlayer);
        this.setBackground(dark);
    }
}