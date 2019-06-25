import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.util.ArrayList;

public class Album {
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<SongGUI> guis = new ArrayList<>();


    public ArrayList<SongGUI> getGuis() {
        return guis;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getPath() {
        return path;
    }

    public int getNum() {
        return num;
    }

    private String name;
    private String artist;
    private String path;
    private int num;
//    PanelGuiInformer informPLGUI=null;

//    public void setInformPLGUI(PanelGuiInformer informPLGUI) {
//        this.informPLGUI = informPLGUI;
//    }

    public Album(String name, ArrayList<Song> songs,ArrayList<SongGUI> songGUIS) {
        this.name = name;
        //getting songs
        this.songs=songs;
        guis=songGUIS;
        num = songs.size();
        artist = songs.get(0).artist;
    }
    public  Album(String name,Song song,SongGUI songGUI){
        this.name=name;
        songs.add(song);
        guis.add(songGUI);
        num=songs.size();
//        informPLGUI.updateGui();
        artist=song.artist;
    }

    public void addSong(Song song,SongGUI gui){
        songs.add(song);
        guis.add(gui);
        num++;
    }
    public void remove(Song song,SongGUI gui){
        songs.remove(song);
        guis.remove(gui);
        num--;
    }
}
