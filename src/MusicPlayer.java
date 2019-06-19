import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MusicPlayer extends Thread implements AddPlaylistListener {
    ArrayList<Playlist> playlists=new ArrayList<>();
    Song currentSong;
    addGUIToCenter listener=null;
    AddToInfoBar InfoBarListener=null;
    Playlist currentPlaylist;
    Playlist recentlyPlayed=new Playlist();
    Playlist favorites=new Playlist();
    InformArtWrok informArtWrok;

    public void setInformArtWrok(InformArtWrok informArtWrok) {
        this.informArtWrok = informArtWrok;
    }

    @Override
    public void run() {
        System.out.println("run running");
        if (currentSong!=null){
                try {
                    currentSong.player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
        }

        }

    public void setListener(addGUIToCenter listener) {
        this.listener = listener;
    }

    @Override
    public void addToPlayList(String path) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException {
        Song song=new Song(path);
        SongGUI gui=new SongGUI(song);
        listener.addGui(gui);
        recentlyPlayed.add(gui,song);
        gui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Song songClicked=recentlyPlayed.getSongByGUI(gui);
                currentSong=songClicked;
                try {
                    play(songClicked);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    public MusicPlayer(){
        currentPlaylist=recentlyPlayed;
    }

    public void setInfoBarListener(AddToInfoBar infoBarListener) {
        InfoBarListener = infoBarListener;
    }

    public void play(Song song) throws JavaLayerException {
        try {
            InfoBarListener.addTOInfo(song);
            informArtWrok.setArtwork(song.artWork);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(song.title);

    }
}
