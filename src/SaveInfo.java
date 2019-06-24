import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaveInfo implements Serializable {
    ArrayList<String> songPaths = new ArrayList<>();
    HashMap<String, String[]> playlistInfo = new HashMap<>(); //play list name And it's image's path
    ArrayList<HashMap<String, Integer>> playlistsAndItsPositiom = new ArrayList<>(); //ArrayList of HashMap of song's playlists and position in playlist
}