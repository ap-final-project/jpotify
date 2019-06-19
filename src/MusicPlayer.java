import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MusicPlayer implements Runnable ,AddPlaylistListener,musicBarListener {
    ArrayList<Playlist> playlists=new ArrayList<>();
    Song currentSong;
    Playlist currentPlaylist;
    boolean continuePlaying;
    Playlist recentlyPlayed=new Playlist();
    Playlist favorites=new Playlist();
    SongGUI currentSongGUI;
    InformCenterSongListener centerSongInformer;
    @Override
    public void run() {
        try {
            while (true) {
                synchronized(this) {
                    while(!continuePlaying)
                        wait();
                    currentSong.player.play();
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private void pause() throws InterruptedException{
        System.out.println("Pause");
        continuePlaying = false;
    }

    private void resumeSong() throws InterruptedException{
        synchronized(this) {
            System.out.println("Resume");
            continuePlaying = true;
            notify();
        }
    }

    @Override
    public void addToPlayList(String path) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException {
        Song song=new Song(path);
        SongGUI gui=new SongGUI(song);
        centerSongInformer.addGUI(gui);
        recentlyPlayed.add(gui,song);
        gui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Song songClicked=recentlyPlayed.getSongByGUI(gui);
                currentSong=songClicked;
                currentSongGUI=gui;
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

    @Override
    public void action(int i) {
        switch (i){
            case 0:
                //resume
                try {
                    resumeSong();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                //pause
                try {
                    pause();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("next");
                //next
                currentSong=currentPlaylist.next(currentSong);
                currentSongGUI=currentPlaylist.next(currentSongGUI);
                //ehtemalan bayad ghablie ro stop konam
                play(currentSong);
                break;
            case 3:
                //pre
                currentSong=currentPlaylist.previous(currentSong);
                currentSongGUI=currentPlaylist.previous(currentSongGUI);
                play(currentSong);
                break;
            case 4:
                //fav
                favorites.add(currentSongGUI,currentSong);
                break;
        }
    }

    public void setCenterSongInformer(InformCenterSongListener centerSongInformer) {
        this.centerSongInformer = centerSongInformer;
    }
}
