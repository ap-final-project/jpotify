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
    @Override
    public void addToPlayList(String path) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException {
        Song song=new Song(path);
        SongGUI gui=new SongGUI(song);
        recentlyPlayed.add(gui,song);
        gui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Song songClicked=recentlyPlayed.playlistInfo.get(gui);
                play(songClicked);
            }
        });
    }
    public MusicPlayer(){
        currentPlaylist=recentlyPlayed;
    }
    public void play(Song song){
        ///inja bayad play beshe
    //be info tuye music bar add she
        //progress bare music bar handle she
        System.out.println("stop clicking me!");
    }
}
