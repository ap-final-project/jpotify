import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MusicPlayer implements Runnable ,AddPlaylistListener {
    ArrayList<Playlist> playlists=new ArrayList<>();
    Song currentSong;
    addGUIToCenter listener=null;
    AddToInfoBar InfoBarListener=null;
    Playlist currentPlaylist;
    int state;
    Playlist recentlyPlayed=new Playlist();
    Playlist favorites=new Playlist();
    @Override
    public void run() {
        if (currentSong!=null){
            switch (state) {
                case 1:
                try {
                    currentSong.player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
                    break;
                case 2:
                    currentSong.player.stop();
                    break;
                case 3:

                    break;
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
                Song songClicked=recentlyPlayed.playlistInfo.get(gui);
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
        InfoBarListener.addTOInfo(song);
        System.out.println(song.title);
    }
}
