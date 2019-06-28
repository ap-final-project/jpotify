import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SaveInfo implements Serializable{

    ArrayList<String> songPaths = new ArrayList<>();
    ArrayList<String> playlistName=new ArrayList<>();
    ArrayList<String> playlistImgPath=new ArrayList<>();
    ArrayList<String> playlistDescription=new ArrayList<>();
    ArrayList<HashMap<String, Integer>> playlistsAndItsPositiom = new ArrayList<>(); //ArrayList of HashMap of song's playlists and position in playlist
}