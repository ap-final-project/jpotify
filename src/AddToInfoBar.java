import javazoom.jl.decoder.JavaLayerException;
import org.jmusixmatch.MusixMatchException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AddToInfoBar {
    void addTOInfo(Song song) throws JavaLayerException, IOException, MusixMatchException;
    void progressBarIncrement(int sec);
}
