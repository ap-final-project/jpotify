import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class Playlist {
    ArrayList<Song> songs;
    ArrayList<SongGUI> guis ;
    String name = "";
    String imgPath = "";
    String description = "";

    public Playlist(String name, String description, String imgPath) {
        this.name = name;
        this.description = description;
        this.imgPath = imgPath;
        songs = new ArrayList<>();
        guis = new ArrayList<>();
    }

    public void add(SongGUI songGUI, Song song) {
        songs.add(song);
        guis.add(songGUI);
        for (PLGUI plGui : CenterPlayLists.playlistGUIs
        ) {
            if (plGui.playlist.equals(this)) {
                plGui.label.setText("<html>" + this.name + "<br>" + this.description + "<br>" + this.songs.size() + "songs" + "</html>");
                break;
            }
        }
    }

    public void remove(Song song) {
        this.songs.remove(song);
        for (PLGUI plGui : CenterPlayLists.playlistGUIs
        ) {
            if (plGui.playlist.equals(this)) {
                plGui.label.setText("<html>" + this.name + "<br>" + this.description + "<br>" + this.songs.size() + "songs" + "</html>");
                break;
            }
        }
    }

    public Song next(Song song) {
        return song;
    }

    public SongGUI next(SongGUI songGUI) {
        return songGUI;
    }

    public Song previous(Song song) {
        return song;
    }

    public SongGUI previous(SongGUI songGUI) {
        return songGUI;
    }

    public Song getNextSong(Song song) {
        int i = songs.indexOf(song);
        if (i != songs.size() - 1)
            return songs.get(i + 1);
        else return songs.get(0);
    }

    public Song getPreSong(Song song) {
        int i = songs.indexOf(song);
        if (i != 0)
            return songs.get(i - 1);
        else return songs.get(songs.size() - 1);
    }

    public Song getSongByGUI(SongGUI songGUI) {
        return songs.get(guis.indexOf(songGUI));
    }

    public SongGUI getGUIBySong(Song song) {
        return guis.get(songs.indexOf(song));
    }

    public void swap(int firtIndex, int secIndex) {
        Song song = songs.get(firtIndex);
        SongGUI songGUI = guis.get(firtIndex);
        System.out.println("moving " + song.title + "from" + firtIndex + "to" + secIndex);
        if (firtIndex > secIndex) {
            songs.remove(song);
            guis.remove(songGUI);
            songs.add(secIndex, song);
            guis.add(secIndex, songGUI);
        } else {
            songs.remove(song);
            guis.remove(songGUI);
            songs.add(secIndex, song);
            guis.add(secIndex, songGUI);
        }
    }
}
