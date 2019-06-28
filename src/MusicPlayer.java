import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sun.source.tree.SynchronizedTree;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class MusicPlayer implements MusicBarListener, AddSong, ProgressBarUpdateListener {
    static Song currentSong;
    PlayBTNListener playBTNListener = null;
    addGUIToCenter addGUIToCenter = null;
    ArrayList<Playlist> playlists;
    AddToInfoBar InfoBarListener = null;
    InformEqualizer informEqualizer;
    MakeVisibilityTrue makeVisibilityTrue = null;
    private long totalFrames;
    private int framesPlayed = 0;
    private int lastSec = 0;
    static Playlist currentPlaylist;
    Playlist recentlyPlayed;
    Playlist favorites;
    InformArtWrok informArtWrok;
    boolean threadStarted = false;
    volatile FileInputStream fis;
    BufferedInputStream bufferedInputStream;
    private long songTotalLength;
    MyPlayer player1;
    Thread playerThread;
    InformSocket informSocket;
    short[] data = new short[64];
    boolean fromThis = true;
    final AtomicBoolean pause = new AtomicBoolean(false);
    boolean firstTime = true;

    public void setPlayBTNListener(PlayBTNListener playBTNListener) {
        this.playBTNListener = playBTNListener;
    }

    public void setInformArtWrok(InformArtWrok informArtWrok) {
        this.informArtWrok = informArtWrok;
    }

    public void setListener(addGUIToCenter addGUIToCenter) {
        this.addGUIToCenter = addGUIToCenter;
    }

    public void setInformSocket(InformSocket informSocket) {
        this.informSocket = informSocket;
    }

    public Thread makeNewThread() {
        return new Thread() {
            @Override
            public void run() {
                try {
                    while (player1.play(1)) {
//                        informEqualizer.sendValues(player1.getFrames());
                        framesPlayed++;
                        if (framesPlayed % 2 == 0) {
                            short[] thisFrame = player1.getFrames();
                            for (int i = 0; i < 32; i++) {
                                data[i] = thisFrame[i];
                            }
                        } else {

                            short[] thisFrame = player1.getFrames();
                            for (int i = 32; i < 64; i++) {
                                data[i] = thisFrame[i - 32];
                            }
                            informEqualizer.sendValues(data);
                        }
                        int sec = player1.audio.getPosition() / 1000;
                        InfoBarListener.progressBarIncrement(sec + lastSec);

                        if (pause.get()) {
                            playBTNListener.clicked(5);
                            LockSupport.park();
                        }
                    }
                    action(2);
                } catch (Exception e) {
                    System.err.printf("%s\n", e.getMessage());
                }
            }
        };
    }

    public MusicPlayer(ArrayList<Playlist> playlists) {
        recentlyPlayed = playlists.get(0);
        favorites = playlists.get(1);
        for (SongGUI gui : recentlyPlayed.guis) {
            if (favorites.songs.contains(gui.song)) {
                gui.song.like();
            }
            gui.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        informSocket.changeSong(gui.song.title,gui.song.artist);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    if (e.getClickCount() == 2) {
                        action(1);

                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        settings(gui, e);
                    } else if (e.getClickCount() == 1) {
                        Song songClicked = recentlyPlayed.getSongByGUI(gui);
                        currentPlaylist = CenterSongs.currentPlayList;
                        if (threadStarted) {
                            if (currentSong != null && !currentSong.equals(songClicked)) {
                                playerThread.stop();
                                threadStarted = false;
                                playerThread = makeNewThread();
                            }
                        }
                        currentSong = songClicked;
                        try {
                            play(songClicked);
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }
                    }

                }

            });
        }
        currentPlaylist = recentlyPlayed;
        this.playlists = playlists;
        playerThread = makeNewThread();
    }

    public void setInformEqualizer(InformEqualizer informEqualizer) {
        this.informEqualizer = informEqualizer;
    }

    public void setInfoBarListener(AddToInfoBar infoBarListener) {
        InfoBarListener = infoBarListener;
    }

    public void play(Song song) throws JavaLayerException {
        if (!threadStarted) {
            try {
                if (currentSong.isLiked()) playBTNListener.clicked(1);//liked
                else playBTNListener.clicked(2);//unliked
                lastSec = 0;
                playBTNListener.clicked(6);//set icon to pause
                fromThis = false;
                player1 = new MyPlayer(new BufferedInputStream(new FileInputStream(currentSong.getPath())));
                totalFrames = player1.findNumbersOfFrame();
                fis = new FileInputStream(song.getPath());
                bufferedInputStream = new BufferedInputStream(fis);
                player1 = new MyPlayer(bufferedInputStream);
                playBTNListener.clicked(4);//reset progress bar
                songTotalLength = currentSong.getTime();
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
                pause.set(!pause.get());
                if (!pause.get()) {
                    playBTNListener.clicked(6);
                    LockSupport.unpark(playerThread);
                }
                break;
            case 2://next
                currentSong = currentPlaylist.getNextSong(currentSong);
                threadStarted = false;
                fromThis = true;
                pause.set(false);
                Thread thread = playerThread;
                playerThread = makeNewThread();
                try {
                    play(currentSong);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                thread.stop();
                try {
                    informSocket.changeSong(currentSong.title,currentSong.artist);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3://previous
                playerThread.stop();
                threadStarted = false;
                fromThis = true;
                pause.set(false);
                currentSong = currentPlaylist.getPreSong(currentSong);
                playerThread = makeNewThread();
                try {
                    play(currentSong);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                try {
                    informSocket.changeSong(currentSong.title,currentSong.artist);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 1:
                //heart clicked
                if (currentSong.isLiked()) {
                    playBTNListener.clicked(2);
                    currentSong.unLike();
                    favorites.remove(currentSong);
                    for (SongGUI songGUI : favorites.guis) {
                        if (songGUI.song.equals(currentSong)) {
                            favorites.guis.remove(songGUI);
                            break;
                        }
                    }
                    makeVisibilityTrue.makeTrue(4);

                } else {
                    playBTNListener.clicked(1);
                    currentSong.like();
                    favorites.add(recentlyPlayed.getGUIBySong(currentSong), currentSong);
                    makeVisibilityTrue.makeTrue(4);
                }
                break;
        }
    }

    @Override
    public void action(int i, SongGUI gui) {
        switch (i) {
            case 0:
                pause.set(!pause.get());
                if (!pause.get()) {
                    playBTNListener.clicked(6);
                    LockSupport.unpark(playerThread);
                }
                break;
            case 2://next
                threadStarted = false;
                fromThis = true;
                pause.set(false);
                currentSong = currentPlaylist.getNextSong(currentSong);
                Thread thread = playerThread;
                playerThread = makeNewThread();
                thread.stop();
                try {
                    play(currentSong);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                try {
                    informSocket.changeSong(currentSong.title,currentSong.artist);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 3://previous
                playerThread.stop();
                threadStarted = false;
                fromThis = true;
                pause.set(false);
                currentSong = currentPlaylist.getPreSong(currentSong);
                playerThread = makeNewThread();
                try {
                    play(currentSong);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                try {
                    informSocket.changeSong(currentSong.title,currentSong.artist);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 1:
                //heart clicked
                if (firstTime) {
                    currentSong = gui.song;
                }
                if (currentSong.isLiked()) {
                    playBTNListener.clicked(2);
                    currentSong.unLike();
                    favorites.remove(currentSong);
                    for (SongGUI songGUI : favorites.guis) {
                        if (songGUI.song.equals(currentSong)) {
                            favorites.guis.remove(songGUI);
                            break;
                        }
                    }
                    makeVisibilityTrue.makeTrue(4);
                } else {
                    playBTNListener.clicked(1);
                    currentSong.like();
                    favorites.add(recentlyPlayed.getGUIBySong(currentSong), currentSong);
                    makeVisibilityTrue.makeTrue(4);

                }
                break;
        }
    }

    private void settings(SongGUI gui, MouseEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem remove = new JMenuItem("remove . . .");
        JMenuItem addPl = new JMenuItem("add to PlayList");
        popupMenu.add(addPl);
        popupMenu.add(remove);
        popupMenu.show(e.getComponent(), e.getX(), e.getY());
        popupMenu.setVisible(true);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CenterSongs.currentPlayList.equals(recentlyPlayed)) {
                    for (Playlist p : playlists) {
                        if (p.guis.contains(gui)) {
                            p.remove(gui.song);
                            p.guis.remove(gui);
                        }
                    }
                    for (int i=0;i<CenterAlbum.albums.size();i++){
                        if(CenterAlbum.albums.get(i).getGuis().contains(gui)){
                            CenterAlbum.albums.get(i).remove(gui.song,gui);
                        }
                    }
//                    for (Album a:)
                } else {
                    CenterSongs.currentPlayList.guis.remove(gui);
                    CenterSongs.currentPlayList.remove(gui.song);
                }
//                for (Album album:)
                makeVisibilityTrue.makeTrue(4);
            }
        });
        addPl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = new JFrame("choose Playlist");
                jFrame.setLayout(new GridLayout(0, 1));
                jFrame.setSize(100, 200);
                jFrame.setLocation(300, 100);
//                            jFrame.setVisible(true);
                for (Playlist p : playlists) {
                    if (p.equals(recentlyPlayed)||p.equals(favorites)) continue;
                    Label label = new Label(p.name, 3);
                    label.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            for (Playlist p : playlists) {
                                if (p.name.equals(label.getText())) {
                                    p.add(gui, gui.song);
                                    if (p.equals(favorites)) {
                                        action(1, gui);
                                    }
                                }
                            }
                        }
                    });
                    jFrame.add(label);
                }
                Button addBtn = new Button("add", 3);
                jFrame.add(addBtn);
                jFrame.setVisible(true);
                addBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jFrame.setVisible(false);
                    }
                });
            }
        });

    }

    @Override
    public void addSong(Song song1, SongGUI songGUI1) {
        Song song;
        song = song1;
        SongGUI gui = songGUI1;
        recentlyPlayed.add(gui, song);
//        addGUIToCenter.addGui(gui);
        makeVisibilityTrue.makeTrue(4);

        gui.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    informSocket.changeSong(gui.song.title,gui.song.artist);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (e.getClickCount() == 2) {
                    action(1);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    settings(gui, e);
                } else if (e.getClickCount() == 1) {
                    Song songClicked = recentlyPlayed.getSongByGUI(gui);
                    currentPlaylist = CenterSongs.currentPlayList;
                    if (threadStarted) {
                        if (currentSong != null && !currentSong.equals(songClicked)) {
                            playerThread.stop();
                            threadStarted = false;
                            playerThread = makeNewThread();
                        }
                    }
                    currentSong = songClicked;
                    try {
                        play(songClicked);
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    }
                }

            }

        });


    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    @Override
    public void update(int percent) {
        try {
            pause.set(!pause.get());
            playerThread.stop();
            lastSec = (int) Math.ceil(percent * songTotalLength / 100);
            int framesToPlay = (int) Math.ceil(percent * totalFrames / 100);
            threadStarted = false;
            player1 = new MyPlayer(new BufferedInputStream(new FileInputStream(currentSong.getPath())));
            for (int i = 0; (i < framesToPlay) && player1.skipFrame(); i++) {
            }
            framesPlayed = framesToPlay;
            pause.set(!pause.get());
            playerThread = makeNewThread();
            threadStarted = true;
            playerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMakeVisibilityTrue(MakeVisibilityTrue makeVisibilityTrue) {
        this.makeVisibilityTrue = makeVisibilityTrue;
    }
}
