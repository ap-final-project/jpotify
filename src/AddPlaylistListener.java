import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.io.File;
import java.io.IOException;

public interface AddPlaylistListener {
void makePlayList(String name,String path);
}