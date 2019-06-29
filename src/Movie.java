
import java.awt.*;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class Movie extends Panel implements MovieBarListener {


    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public Movie() {
        super(2);
        this.setLayout(new BorderLayout());
        new NativeDiscovery().discover();
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        this.add(mediaPlayerComponent, BorderLayout.CENTER);

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
        mediaPlayerComponent.getMediaPlayer().stop();
    }
}