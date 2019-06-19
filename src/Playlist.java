import java.util.*;

public class Playlist {

    ArrayList<Song> songs = new ArrayList<>();
    ArrayList<SongGUI> guis = new ArrayList<>();

    public Playlist() {

    }

    public void add(SongGUI songGUI, Song song) {
        songs.add(song);
        guis.add(songGUI);
    }

    public Song next(Song current) {
        int index = songs.indexOf(current);
        if (index < songs.size() && index>0)
            return songs.get(songs.indexOf(current) + 1);
        return null;
    }

    public SongGUI next(SongGUI current) {
        int index = guis.indexOf(current);
        if (index < guis.size()&& index>0)
            return guis.get(guis.indexOf(current) + 1);
        return null;
    }


    public Song previous(Song current) {
        int index = songs.indexOf(current);
        if (index >0)
            return songs.get(songs.indexOf(current) - 1);
        return null;
    }

    public SongGUI previous(SongGUI current) {
        int index = guis.indexOf(current);
        if (index >0)
            return guis.get(guis.indexOf(current) - 1);
        return null;
    }
    public Song getSongByGUI(SongGUI songGUI) {
        int index = guis.indexOf(songGUI);
        if (index < guis.size())
            return songs.get(guis.indexOf(songGUI) + 1);
        System.out.println("index put of bound!");
        return null;
    }

    public SongGUI getGUIBySong(Song song) {
        int index = songs.indexOf(song);
        if (index < songs.size())
            return guis.get(songs.indexOf(song) + 1);
        System.out.println("index put of bound!");
        return null;
    }
}

