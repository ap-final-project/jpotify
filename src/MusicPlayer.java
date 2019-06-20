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
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class MusicPlayer implements AddPlaylistListener, MusicBarListener {
    Song currentSong;
    PlayBTNListener playBTNListener = null;
    addGUIToCenter listener = null;
    AddToInfoBar InfoBarListener = null;
    Playlist currentPlaylist;
    Playlist recentlyPlayed = new Playlist();
    Playlist favorites = new Playlist();
    InformArtWrok informArtWrok;
    boolean threadStarted = false;
    volatile FileInputStream fis;
    BufferedInputStream bufferedInputStream;
    private long songTotalLength;
    Player player1;
    private long part;
    Thread playerThread;
    boolean fromThis = true;
    private float progress=0;
    private int i=0;
    final AtomicBoolean pause = new AtomicBoolean(false);

    public void setPlayBTNListener(PlayBTNListener playBTNListener) {
        this.playBTNListener = playBTNListener;
    }
    public void setInformArtWrok(InformArtWrok informArtWrok) {
        this.informArtWrok = informArtWrok;
    }
    public void setListener(addGUIToCenter listener) {
        this.listener = listener;
    }

    public void makeNewThread(){
        playerThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (player1.play(1)) {
                        int temp=(player1.getPosition()/1000)+1;
                        if (progress<temp) {
                            progress++;
                            System.out.println("hi"+i);
                            i++;
                            playBTNListener.clicked(3);
                        }
                        if (pause.get()) {
                            LockSupport.park();
                        }
                    }
                } catch (Exception e) {
                    System.err.printf("%s\n", e.getMessage());
                }
            }
        };
    }
    @Override
    public void addToPlayList(String path) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException {
        Song song = new Song(path);
        SongGUI gui = new SongGUI(song);
        listener.addGui(gui);
        recentlyPlayed.add(gui, song);
        fis = new FileInputStream(song.getPath());
        bufferedInputStream = new BufferedInputStream(fis);
        gui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Song songClicked = recentlyPlayed.getSongByGUI(gui);
                if (threadStarted) {
                    if (currentSong != null && !currentSong.equals(songClicked)) {
                        playerThread.stop();
                        threadStarted = false;
                        makeNewThread();
                    }
                }
                currentSong = songClicked;
                try {
                    play(songClicked);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
            }

        });
    }

    public MusicPlayer() throws JavaLayerException {
        currentPlaylist = recentlyPlayed;
        makeNewThread();
    }

    public void setInfoBarListener(AddToInfoBar infoBarListener) {
        InfoBarListener = infoBarListener;
    }

    public void play(Song song) throws JavaLayerException {
        if (!threadStarted) {
            try {
                if (currentSong.isLiked()) playBTNListener.clicked(1);
                else playBTNListener.clicked(2);
                playBTNListener.clicked(0);
                fromThis = false;
                fis = new FileInputStream(song.getPath());
                bufferedInputStream = new BufferedInputStream(fis);
                player1 = new Player(bufferedInputStream);
                playBTNListener.clicked(4);
                progress=0;
                songTotalLength=currentSong.getTime();
                threadStarted = true;
                informArtWrok.setArtwork(song.artWork);
                InfoBarListener.addTOInfo(song);
                playerThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void action(int i) {
        switch (i) {
            case 0:
                if(!fromThis){
                    pause.set(!pause.get());
                    if (!pause.get()) {
                        LockSupport.unpark(playerThread);
                    }
                }
                fromThis = false;
                break;
            case 2:
                playerThread.stop();
                threadStarted = false;
                fromThis=true;
                pause.set(false);
                currentSong=recentlyPlayed.getNextSong(currentSong);
                makeNewThread();
                try {
                    play(currentSong);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                    break;
            case 3:
                playerThread.stop();
                threadStarted = false;
                fromThis=true;
                pause.set(false);
                currentSong=recentlyPlayed.getPreSong(currentSong);
                makeNewThread();
                try {
                    play(currentSong);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                break;
            case 1:
                if (currentSong.isLiked()) {
                    playBTNListener.clicked(2);
                    currentSong.unLike();
                    //remove from favorites
                }
                else {
                    playBTNListener.clicked(1);
                    currentSong.like();
                    favorites.add(recentlyPlayed.getGUIBySong(currentSong),currentSong);
                }
                break;
        }
    }
}
