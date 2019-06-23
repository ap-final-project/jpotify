import javazoom.jl.decoder.JavaLayerException;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MusicBar extends Panel implements AddToInfoBar,PlayBTNListener {
    Button play;
    Button stop;
    Button next;
    Button previous;
    Button shuffle;
    Button repeat;
    Button like;
    Label songName = new Label(3);
    Label artistName = new Label(3);
    Label album = new Label(3);
    Label year = new Label(3);
    ImageIcon playIcon=new ImageIcon("img\\play.png");
    ImageIcon pauseIcon=new ImageIcon("img\\pause.png");
    ImageIcon fullHeartIcon=new ImageIcon("img\\fullHeart.png");
    ImageIcon emptyHeartIcon=new ImageIcon("img\\emptyHeart.png");
    Label sound=new Label(3);
    ImageIcon loud=new ImageIcon("img\\loud.png");
    ImageIcon low=new ImageIcon("img\\low.png");
    ImageIcon mute=new ImageIcon("img\\mute.png");
    Panel info;
    JSlider jSlider=new JSlider();

    progress progressBar;
    MusicBarListener musicBarListener;
    private float songLength;
    ProgressBarUpdateListener barUpdateListener;
    private boolean isPlaying=false;
    public void setBarUpdateListener(ProgressBarUpdateListener barUpdateListener) {
        this.barUpdateListener = barUpdateListener;
    }

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
        stop = new Button(3);
        next = new Button(3);
        previous = new Button(3);
        repeat = new Button(3);
        shuffle = new Button(3);
        like = new Button(3);
        play.setIcon(playIcon);
        previous.setIcon(new ImageIcon("img\\back.png"));
        next.setIcon(new ImageIcon("img\\next.png"));
        shuffle.setIcon(new ImageIcon("img\\shuffle.png"));
        repeat.setIcon(new ImageIcon("img\\repeat.png"));
        like.setIcon(emptyHeartIcon);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        jSlider.setBackground(new Color(40,40,40));
        jSlider.setForeground(new Color(194,194,194));
        jSlider.setOpaque(true);
        jSlider.setPaintTicks(true);
        jSlider.setUI(new LightSliderUI(jSlider));

//        jSlider.setPaintTicks(false);
        up.setLayout(flowLayout);
        up.add(like);
        up.add(shuffle);
        up.add(previous);
        up.add(play);
        up.add(next);
        up.add(repeat);
        up.add(sound);
        up.add(jSlider);
        Panel keke = new Panel(3);
        keke.setBorder(new EmptyBorder(10, 10, 10, 10));
        keke.setLayout(new BorderLayout());
        keke.add(up, BorderLayout.PAGE_START);
        progressBar.setBorder(new EmptyBorder(10, 0, 0, 0));
        keke.add(progressBar, BorderLayout.PAGE_END);
        this.add(keke, BorderLayout.CENTER);
        this.setListeners();
    }

    public void addInfo() {
        info.add(songName);
        info.add(artistName);
        info.add(album);
        info.add(year);
    }

    public void setMusicBarListener(MusicBarListener musicBarListener) {
        this.musicBarListener = musicBarListener;
    }

    @Override
    public void addTOInfo(Song song) throws JavaLayerException, IOException {
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
                    if (value>0 && value<0.33) sound.setIcon(mute);
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
}