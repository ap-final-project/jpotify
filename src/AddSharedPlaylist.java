import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.util.ArrayList;

public interface AddSharedPlaylist {
    void addSharedPlaylist(ArrayList<String> paths,String ip) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException;
}
