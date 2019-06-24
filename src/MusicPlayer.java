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
    Song currentSong;
    PlayBTNListener playBTNListener = null;
    addGUIToCenter addGUIToCenter = null;
    ArrayList<Playlist> playlists = new ArrayList<>();
    AddToInfoBar InfoBarListener = null;
    InformEqualizer informEqualizer;
    MakeVisibilityTrue makeVisibilityTrue = null;
    private long totalFrames;
    private int framesPlayed = 0;
    private int lastSec = 0;


    public void setMakeVisibilityTrue(MakeVisibilityTrue makeVisibilityTrue) {
        this.makeVisibilityTrue = makeVisibilityTrue;
    }

    static Playlist currentPlaylist;
    Playlist recentlyPlayed = new Playlist("recentlyPlayed", "All songs", "img\\play.png");
    Playlist favorites = new Playlist("favorites", "Your favorite songs", "img\\fullHeart.png");
    InformArtWrok informArtWrok;
    boolean threadStarted = false;
    volatile FileInputStream fis;
    BufferedInputStream bufferedInputStream;
    private long songTotalLength;
    MyPlayer player1;
    Thread playerThread;
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


    public void makeNewThread() {
        playerThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (player1.play(1)) {
                        informEqualizer.sendValues(player1.getFrames());
                        framesPlayed++;
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

    public MusicPlayer() throws JavaLayerException {
        currentPlaylist = recentlyPlayed;
        playlists.add(recentlyPlayed);
        playlists.add(favorites);
        makeNewThread();
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
                playerThread.stop();
                makeNewThread();
                try {
                    play(currentSong);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                break;
            case 3://previous
                playerThread.stop();
                threadStarted = false;
                fromThis = true;
                pause.set(false);
                currentSong = currentPlaylist.getPreSong(currentSong);
                makeNewThread();
                try {
                    play(currentSong);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                break;
            case 1:
                //heart clicked
                if (currentSong.isLiked()) {
                    playBTNListener.clicked(2);
                    currentSong.unLike();
                    //remove from favorites
                } else {
                    playBTNListener.clicked(1);
                    currentSong.like();
                    favorites.add(recentlyPlayed.getGUIBySong(currentSong), currentSong);
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
                playerThread.stop();
                threadStarted = false;
                fromThis = true;
                pause.set(false);
                currentSong = currentPlaylist.getNextSong(currentSong);
                makeNewThread();
                try {
                    play(currentSong);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                break;
            case 3://previous
                playerThread.stop();
                threadStarted = false;
                fromThis = true;
                pause.set(false);
                currentSong = currentPlaylist.getPreSong(currentSong);
                makeNewThread();
                try {
                    play(currentSong);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
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
                    //remove from favorites
                } else {
                    playBTNListener.clicked(1);
                    currentSong.like();
                    favorites.add(recentlyPlayed.getGUIBySong(currentSong), currentSong);
                }
                break;
        }
    }

    private void settings(SongGUI gui, MouseEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem remove = new JMenuItem("remove . . .");
        JMenuItem addPl = new JMenuItem("add to PlayList");
        JMenuItem kian = new JMenuItem("kian");
        popupMenu.add(addPl);
        popupMenu.add(remove);
        popupMenu.add(kian);
        popupMenu.show(e.getComponent(), e.getX(), e.getY());
        popupMenu.setVisible(true);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("removing");
                if(CenterSongs.currentPlayList.equals(recentlyPlayed)) {
                    for (Playlist p:playlists) {
                        if(p.guis.contains(gui)){
                            p.remove(gui.song);
                            p.guis.remove(gui);
                        }
                    }
                }
                else{
                    CenterSongs.currentPlayList.guis.remove(gui);
                    CenterSongs.currentPlayList.remove(gui.song);
                }
                makeVisibilityTrue.makeTrue(0);
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
                    if (p.equals(recentlyPlayed)) continue;
                    Label label = new Label(p.name, 3);
                    label.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            for (Playlist p : playlists) {
                                if (p.name.equals(label.getText())) {
                                    p.add(gui, gui.song);
                                    if (p.equals(favorites)) {
                                        gui.song.like();
                                        action(1, gui);
                                        playBTNListener.clicked(1);
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
        addGUIToCenter.addGui(gui);
        gui.more.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                settings(gui, e);
            }
        });
        gui.checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) System.out.println(gui.song.title);
            }
        });
        gui.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
//                        if (!gui.song.isLiked()) {
                    gui.song.like();
                    action(1);
                    playBTNListener.clicked(1);
//                        }
//                        else{
//                            gui.song.unLike();
//                            playBTNListener.clicked(2);
//                        }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    settings(gui, e);
                } else if (e.getClickCount() == 1) {
                    Song songClicked = recentlyPlayed.getSongByGUI(gui);
                    currentPlaylist = CenterSongs.currentPlayList;
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

            }

        });


    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    @Override
    public void update(int percent) throws JavaLayerException {
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
            makeNewThread();
            threadStarted = true;
            playerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
