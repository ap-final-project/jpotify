import uk.co.caprica.vlcj.player.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovieBar extends Panel{
    private Button pauseButton;
    private Button rewindButton;
    private Button skipButton;
    private Button play;
    private Button stop;
    private String title;
    private Label label;
    private Label sound=new Label(3);
    private ImageIcon loud=new ImageIcon("img\\loud.png");
    private ImageIcon low=new ImageIcon("img\\low.png");
    private ImageIcon mute=new ImageIcon("img\\mute.png");
    private JSlider jSlider=new JSlider();
    private MovieBarListener movieBarListener;
    public  MovieBar(){
        super(2);
        this.setLayout(new BorderLayout());
        pauseButton=new Button(3);
        rewindButton=new Button(3);
        skipButton=new Button(3);
        play=new Button(3);
        stop=new Button(3);
        jSlider.setBackground(new Color(40,40,40));
        jSlider.setForeground(new Color(194,194,194));
        jSlider.setOpaque(true);
        jSlider.setPaintTicks(true);
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
        this.add(volume,BorderLayout.EAST);
        jSlider.setUI(new LightSliderUI(jSlider));
        Panel up = new Panel(3);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        up.setLayout(flowLayout);
        pauseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movieBarListener.pause();
            }
        });
        skipButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movieBarListener.skip();
            }
        });
        rewindButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movieBarListener.rewind();
            }
        });
        stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movieBarListener.close();
            }
        });
        pauseButton.setIcon(new ImageIcon("img\\pause.png"));
        skipButton.setIcon(new ImageIcon("img\\next.png"));
        rewindButton.setIcon(new ImageIcon("img\\back.png"));
        play.setIcon(new ImageIcon("img\\play.png"));
        stop.setIcon(new ImageIcon("img\\stop.png"));
        up.add(rewindButton);
        up.add(stop);
        up.add(pauseButton);
        up.add(skipButton);
        this.add(up,BorderLayout.CENTER);
    }
    public void setMovieBarListener(MovieBarListener movieBarListener) {
        this.movieBarListener = movieBarListener;
    }
}
