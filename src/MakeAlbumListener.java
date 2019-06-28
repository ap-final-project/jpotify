import java.util.ArrayList;

public interface MakeAlbumListener {
    void makeAlbum(String name, ArrayList<Song> songs,ArrayList<SongGUI> songGUIS);
    void makeAlbumS(String name, Song song,SongGUI songGUI);
    void removeAlbum(Album album);
}
