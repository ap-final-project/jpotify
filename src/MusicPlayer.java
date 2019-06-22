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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class MusicPlayer implements MusicBarListener, AddSong,ProgressBarUpdateListener {
    Song currentSong;
    PlayBTNListener playBTNListener = null;
    addGUIToCenter addGUIToCenter = null;
    ArrayList<Playlist> playlists = new ArrayList<>();
    AddToInfoBar InfoBarListener = null;
    InformEqualizer informEqualizer;
    MakeVisibilityTrue makeVisibilityTrue = null;
    private long totalFrames;
    private int framesPlayed=0;
    private int lastSec=0;
    public void setMakeVisibilityTrue(MakeVisibilityTrue makeVisibilityTrue) {
        this.makeVisibilityTrue = makeVisibilityTrue;
    }

    Playlist currentPlaylist;
    Playlist recentlyPlayed = new Playlist("recentlyPlayed", "img\\play.png");
    Playlist favorites = new Playlist("favorites", "img\\fullHeart.png");
    InformArtWrok informArtWrok;
    boolean threadStarted = false;
    volatile FileInputStream fis;
    BufferedInputStream bufferedInputStream;
    private long songTotalLength;
    MyPlayer player1;
    Thread playerThread;
    boolean fromThis = true;
    final AtomicBoolean pause = new AtomicBoolean(false);

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
                        int sec=player1.audio.getPosition()/1000;
                        InfoBarListener.progressBarIncrement(sec+lastSec);

                        if (pause.get()) {
                            playBTNListener.clicked(5);
                            LockSupport.park();
                        }
                    }
                    if (player1.isComplete())action(2);
                } catch (Exception e) {
                    System.err.printf("%s\n", e.getMessage());
                }
            }
        };
    }

/*    @Override
    public void addToPlayList(String path) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException {
        Song song = new Song(path);
        SongGUI gui = new SongGUI(song);
        recentlyPlayed.add(gui, song);
        //shall remove
//        for (SongGUI songGUI:recentlyPlayed.guis) {
//            listener.addGui(gui);
//        }

        fis = new FileInputStream(song.getPath());
        bufferedInputStream = new BufferedInputStream(fis);
        gui.more.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                PLGUI chosed=null;
                JFrame jFrame=new JFrame("choose Player");
                jFrame.setLayout(new GridLayout(0,1));
                jFrame.setSize(100,200);
                jFrame.setLocation(300,100);
                for (Playlist p:playlists) {
                    Label label=new Label(p.name,3);
                    label.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
//                            super.mouseClicked(e);
                            System.out.println(label.getText());
                            for (Playlist p:playlists) {
                                if(p.name.equals(label.getText())){
                                    p.add(gui,gui.song);
                                    for (SongGUI songGUI:p.guis) {
                                        System.out.println(p.guis.size());
                                        addPlSongs.addGui(gui);
                                    }
                                    if(p.equals(favorites)){
                                        song.like();
                                        playBTNListener.clicked(1);
                                    }
                                }
                            }
                        }
                    });
                    jFrame.add(label);
                }
                Button addBtn=new Button("add",3);
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
        gui.checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==1) System.out.println(gui.song.title);
            }
        });
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
*/

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
                playerThread.stop();
                threadStarted = false;
                fromThis = true;
                pause.set(false);
                currentSong = recentlyPlayed.getNextSong(currentSong);
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
                currentSong = recentlyPlayed.getPreSong(currentSong);
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
    public void addSong(String path) {
        Song song;
        try {
            song = new Song(path);
            SongGUI gui = new SongGUI(song);
            recentlyPlayed.add(gui, song);
            addGUIToCenter.addGui(gui);
            gui.more.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem remove = new JMenuItem("remove . . .");
                    JMenuItem addPl = new JMenuItem("add to PlayList");
                    JMenuItem kian = new JMenuItem("kian");
                    popupMenu.add(addPl);
                    popupMenu.add(remove);
                    popupMenu.add(kian);
                    popupMenu.show(gui.more, e.getX(), e.getY());
                    popupMenu.setVisible(true);
                    addPl.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("dare mirize . . .");
                            JFrame jFrame=new JFrame("choose Player");
                            jFrame.setLayout(new GridLayout(0,1));
                            jFrame.setSize(100,200);
                            jFrame.setLocation(300,100);
//                            jFrame.setVisible(true);
                            for (Playlist p:playlists) {
                                Label label=new Label(p.name,3);
                                label.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
//                            super.mouseClicked(e);
                                        System.out.println(label.getText());
                                        for (Playlist p:playlists) {
                                            if(p.name.equals(label.getText())){
                                                p.add(gui,gui.song);
                                                for (SongGUI songGUI:p.guis) {
                                                    System.out.println(p.guis.size());
//                                                    addPlSongs.addGui(gui);
                                                }
                                                if(p.equals(favorites)){
                                                    song.like();
                                                    playBTNListener.clicked(1);
                                                }
                                            }
                                        }
                                    }
                                });
                                jFrame.add(label);
                            }
                            Button addBtn=new Button("add",3);
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
                    /*

*/
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

        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    @Override
    public void update(int percent) throws JavaLayerException {
        try {
            pause.set(!pause.get());
            playerThread.stop();
            lastSec= (int) Math.ceil(percent*songTotalLength/100);
            int framesToPlay= (int) Math.ceil(percent * totalFrames/100);
            System.out.println("percent: "+percent+"total frames"+totalFrames+" frames to play"+framesToPlay);
            threadStarted = false;
            player1 = new MyPlayer(new BufferedInputStream(new FileInputStream(currentSong.getPath())));
            for (int i = 0; (i < framesToPlay) && player1.skipFrame(); i++) {
            }
            framesPlayed=framesToPlay;
            pause.set(!pause.get());
            makeNewThread();
            threadStarted = true;
            playerThread.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
