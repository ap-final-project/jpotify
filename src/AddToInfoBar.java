import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AddToInfoBar {
    void addTOInfo(Song song) throws JavaLayerException, IOException;
}
