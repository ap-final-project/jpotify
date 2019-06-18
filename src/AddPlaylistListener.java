import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.io.File;
import java.io.IOException;

public interface AddPlaylistListener {
public void addToPlayList(String path) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException;
}
