import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class Playlist {
HashMap<SongGUI,Song> playlistInfo=new HashMap<>();
    public Playlist(){

}
public void add(SongGUI songGUI,Song song){
    playlistInfo.put(songGUI,song);
}
}
