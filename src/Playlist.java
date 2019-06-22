import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class Playlist {
ArrayList<Song> songs=new ArrayList<>();
ArrayList<SongGUI> guis=new ArrayList<>();
String name="";
String imgPath="";
    public Playlist(String name,String imgPath){
        this.name=name;
        this.imgPath=imgPath;
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
    public Song getNextSong(Song song){
        int i=songs.indexOf(song);
        if(i!=songs.size()-1)
        return songs.get(i+1);
        else return songs.get(0);
    }
    public Song getPreSong(Song song){
        int i=songs.indexOf(song);
        if(i!=0)
            return songs.get(i-1);
        else return songs.get(songs.size()-1);
    }
    public Song getSongByGUI(SongGUI songGUI){
        return songs.get(guis.indexOf(songGUI));
    }
    public SongGUI getGUIBySong(Song song){
        return guis.get(songs.indexOf(song));
    }

}
