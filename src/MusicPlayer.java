import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sun.source.tree.SynchronizedTree;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class MusicPlayer extends Thread implements AddPlaylistListener,MusicBarListener {
    Song currentSong;
    addGUIToCenter listener=null;
    AddToInfoBar InfoBarListener=null;
    Playlist currentPlaylist;
    Playlist recentlyPlayed=new Playlist();
    Playlist favorites=new Playlist();
    InformArtWrok informArtWrok;
    boolean threadStarted=false;
    volatile FileInputStream fis;
    BufferedInputStream bufferedInputStream;
    volatile AdvancedPlayer player;
    private long pauseLocation;
    private long songTotalLength;
    public void setInformArtWrok(InformArtWrok informArtWrok) {
        this.informArtWrok = informArtWrok;
    }
    boolean isPaused=false;
    @Override
    public synchronized void run() {
        System.out.println("run running");
        System.out.println("pausedLocation"+pauseLocation);
        System.out.println("total"+songTotalLength);
        try {
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void setListener(addGUIToCenter listener) {
        this.listener = listener;
    }
    public void pause(){
        if (player != null) {
            try {
                System.out.println("pausing!");
                pauseLocation=fis.available();
                System.out.println("paueseed"+pauseLocation);
                isPaused=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
            System.out.println("what the heck");
        player.close();
        this.stop();
        if (player==null) System.out.println("nulle");
    }
    public void stopPLayer(){
        if (player!=null) {
            this.stop();        }
    }
    public void playPlayer(){
        try {
            fis=new FileInputStream(currentSong.getPath());
            player=new AdvancedPlayer(fis);
            songTotalLength=fis.available();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resumePlayer(){
        try {
            fis=new FileInputStream(currentSong.getPath());
            bufferedInputStream=new BufferedInputStream(fis);
            player=new AdvancedPlayer(bufferedInputStream);
//            fis.skip(songTotalLength-pauseLocation);
            if (isPaused)
                run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
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

    public  void  play(Song song) throws JavaLayerException {
        if (!threadStarted) {
            try {
                playPlayer();
                this.start();
                InfoBarListener.addTOInfo(song);
                informArtWrok.setArtwork(song.artWork);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(song.title);
        }
    }

    @Override
    public void action(int i) {
        switch (i){
            case 0:
                System.out.println("play");
                resumePlayer();
                break;
            case 1:
                System.out.println("stop");
                pause();
                break;
            case 2:
                stopPLayer();
                break;
            case 3:
                //pre
                break;
            case 4:
                break;
        }
    }
}
