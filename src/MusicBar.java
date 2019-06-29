import javazoom.jl.decoder.JavaLayerException;
import org.jmusixmatch.MusixMatchException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MusicBar extends Panel implements AddToInfoBar,PlayBTNListener {
    private Button play;
    private Button next;
    private Button previous;
    private Button shuffle;
    private Button repeat;
    private Button like;
    private Button getLyric;
    private Label songName = new Label(3);
    private Label artistName = new Label(3);
    private Label album = new Label(3);
    private Label year = new Label(3);
    private ImageIcon playIcon=new ImageIcon("img\\play.png");
    private ImageIcon pauseIcon=new ImageIcon("img\\pause.png");
    private ImageIcon fullHeartIcon=new ImageIcon("img\\fullHeart.png");
    private ImageIcon emptyHeartIcon=new ImageIcon("img\\emptyHeart.png");
    private ImageIcon onRepeat=new ImageIcon("img\\repeat.png");
    private ImageIcon repeatOff=new ImageIcon("img\\repeatOff.png");
    private Label sound=new Label(3);
    private ImageIcon loud=new ImageIcon("img\\loud.png");
    private ImageIcon low=new ImageIcon("img\\low.png");
    private ImageIcon mute=new ImageIcon("img\\mute.png");
    private ImageIcon shuffleOff=new ImageIcon("img\\shuffle.png");
    private ImageIcon shuffleOn=new ImageIcon("img\\shuffleOn.png");
    private Panel info;
    private JSlider jSlider=new JSlider();
    private progress progressBar;
    private MusicBarListener musicBarListener;
    private float songLength;
    private ProgressBarUpdateListener barUpdateListener;
    private boolean isPlaying=false;
    private boolean onShuffle;
    public MusicBar() {
        super(3);
        this.setLayout(new BorderLayout());
        info = new Panel(3);
        info.setLayout(new GridLayout(5, 1));
        info.setSize(new Dimension(100, 100));
        Panel up = new Panel(3);
        progressBar = new progress();
        this.add(info, BorderLayout.WEST);
        play = new Button(3);
        next = new Button(3);
        getLyric=new Button("getLyric",3);
        previous = new Button(3);
        repeat = new Button(3);
        shuffle = new Button(3);
        like = new Button(3);
        play.setIcon(playIcon);
        shuffle.setIcon(shuffleOff);
        previous.setIcon(new ImageIcon("img\\back.png"));
        next.setIcon(new ImageIcon("img\\next.png"));
        repeat.setIcon(repeatOff);
        like.setIcon(emptyHeartIcon);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        jSlider.setBackground(new Color(40,40,40));
        jSlider.setForeground(new Color(194,194,194));
        jSlider.setOpaque(true);
        jSlider.setPaintTicks(true);
        jSlider.setUI(new LightSliderUI(jSlider));
        Panel volume=new Panel(3);
        BorderLayout layout=new BorderLayout();
        layout.setHgap(10);
        volume.setLayout(layout);
        sound.setBorder(BorderFactory.createEmptyBorder(0,0,8,0));
        volume.setPreferredSize(new Dimension(120,30));
        sound.setIcon(low);
        volume.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
        volume.add(sound,BorderLayout.WEST);
        volume.add(jSlider,BorderLayout.CENTER);
        up.setLayout(flowLayout);
        up.add(like);
        up.add(shuffle);
        up.add(previous);
        up.add(play);
        up.add(next);
        up.add(repeat);
        up.add(getLyric);
        this.add(volume,BorderLayout.EAST);
        Panel lowerPanel = new Panel(3);
        this.add(lowerPanel, BorderLayout.CENTER);
        lowerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        lowerPanel.setLayout(new BorderLayout());
        lowerPanel.add(up, BorderLayout.PAGE_START);
        lowerPanel.add(progressBar, BorderLayout.PAGE_END);
        progressBar.setBorder(new EmptyBorder(10, 0, 0, 0));
        this.setListeners();
        getLyric.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Lyrics lyrics=new Lyrics(songName.getText().trim(),artistName.getText().trim());
            }
        });
    }

    public void addInfo() {
        info.add(songName);
        info.add(artistName);
        info.add(album);
        info.add(year);
    }

    @Override
    public void addTOInfo(Song song) {
        songName.setText(song.title);
        artistName.setText(song.artist);
        album.setText(song.album);
        year.setText(song.year);
        this.add(info, BorderLayout.WEST);
        this.addInfo();
        songLength= song.getTime();
        isPlaying=true;
        revalidate();
    }

    @Override
    public void progressBarIncrement(int sec) {
        progressBar.setV((int) Math.ceil(sec*100/songLength));
        progressBar.setTime(sec);
    }

    private void setListeners(){
        repeat.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (MusicPlayer.onRepeat){
                    MusicPlayer.onRepeat=false;
                    repeat.setIcon(repeatOff);
                }else
                {
                    MusicPlayer.onRepeat=true;
                    repeat.setIcon(onRepeat);
                }
            }
        });
        shuffle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (onShuffle){
                    shuffle.setIcon(shuffleOff);
                    onShuffle=false;
                    musicBarListener.action(5);

                }else {
                    shuffle.setIcon(shuffleOn);
                    onShuffle=true;
                    musicBarListener.action(4);
                }
            }
        });
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musicBarListener.action(0);
            }
        });
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musicBarListener.action(2);
            }
        });
        previous.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musicBarListener.action(3);
            }
        });
        like.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musicBarListener.action(1);
            }
        });
        progressBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    if (isPlaying) {
                        int relativeX = e.getLocationOnScreen().x - progressBar.getBarLocationOnScreen();
                        barUpdateListener.update((int) Math.ceil(relativeX * 100 / progressBar.getBarWidth()));
                        progressBar.setV((int) Math.ceil(relativeX * 100 / progressBar.getBarWidth()));
                    }
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
            }
        });

        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider slider=(JSlider) e.getSource();
                float value = slider.getValue() / 100.0f;
                try {
                    getVolumeControl().setValue(value);
                    if (value>=0 && value<0.33) sound.setIcon(mute);
                    else if (value>=0.33 && value<0.66) sound.setIcon(low);
                    else sound.setIcon(loud);
                    //you can put a click play code here to have nice feedback when moving slider
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    @Override
    public void clicked(int i) {
        switch (i) {
            case 1:
                like.setIcon(new ImageIcon("img\\fullHeart.png"));
                break;
            case 2:
                like .setIcon(new ImageIcon("img\\emptyHeart.png"));
                break;
            case 4:
                progressBar.reset();
                break;
            case 5:
                //play
                play.setIcon(playIcon);
                break;
            case 6:
                //pause
                play.setIcon(pauseIcon);
                break;
        }
    }

    private FloatControl getVolumeControl() throws Exception {
        try {
            Mixer.Info mixers[] = AudioSystem.getMixerInfo();
            for (Mixer.Info mixerInfo : mixers) {
                Mixer mixer = AudioSystem.getMixer(mixerInfo);
                mixer.open();
                for (Line.Info info : mixer.getTargetLineInfo()) {
                    if (info.toString().contains("SPEAKER")) {
                        Line line = mixer.getLine(info);
                        try {
                            line.open();
                        } catch (IllegalArgumentException iae) {}
                        return (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("problem creating volume control object:"+ex);
            throw ex;
        }
        throw new Exception("unknown problem creating volume control object");
    }
    public void setMusicBarListener(MusicBarListener musicBarListener) {
        this.musicBarListener = musicBarListener;
    }

    public void setBarUpdateListener(ProgressBarUpdateListener barUpdateListener) {
        this.barUpdateListener = barUpdateListener;
    }
}