import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class Playlist {
ArrayList<Song> songs=new ArrayList<>();
ArrayList<SongGUI> guis=new ArrayList<>();
    public Playlist(){

        }
    public void add(SongGUI songGUI,Song song){
    songs.add(song);
    guis.add(songGUI);
    }
    public Song next(Song song){
        return song;
    }

    public SongGUI next(SongGUI songGUI){
        return songGUI;
    }

    public Song previous(Song song){
        return song;
    }

    public SongGUI previous(SongGUI songGUI){
        return songGUI;
    }

}
