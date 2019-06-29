
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

public class Movie extends Panel implements MovieBarListener {


    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
//
//    public static void main(final String[] args) {
//        new NativeDiscovery().discover();
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Tutorial(args);
//            }
//        });
//    }

    public Movie() {
        super(2);
        this.setLayout(new BorderLayout());
        new NativeDiscovery().discover();
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        this.add(mediaPlayerComponent, BorderLayout.CENTER);
        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {

            @Override
            public void finished(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        closeWindow();
                    }
                });
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
//                        JOptionPane.showMessageDialog(
//                                frame,
//                                "Failed to play media",
//                                "Error",
//                                JOptionPane.ERROR_MESSAGE
//                        );
//                        closeWindow();
                    }
                });
            }
        });

    }

    @Override
    public void pause() {
        mediaPlayerComponent.getMediaPlayer().pause();
    }

    @Override
    public void rewind() {
        mediaPlayerComponent.getMediaPlayer().skip(-10000);

    }

    @Override
    public void skip() {
        mediaPlayerComponent.getMediaPlayer().skip(10000);

    }

    @Override
    public void play(String path) {

        mediaPlayerComponent.getMediaPlayer().playMedia(path);
    }

    @Override
    public void close() {

    }

    private void closeWindow() {
        //choose video panel
}
    }
