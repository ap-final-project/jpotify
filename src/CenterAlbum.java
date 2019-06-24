import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CenterAlbum extends Panel implements MakeAlbumListener{
    ArrayList<Album> albums = new ArrayList<>();
    ChoosePlaylist choosePlaylist = null;
    MakeVisibilityTrue makeVisibilityTrue = null;
    boolean flag=false;

    public void setMakeVisibilityTrue(MakeVisibilityTrue makeVisibilityTrue) {
        this.makeVisibilityTrue = makeVisibilityTrue;
    }

    public CenterAlbum() {
        super(3);
        this.setLayout(new WrapLayout(WrapLayout.CENTER));
    }

    public void setChoosePlaylist(ChoosePlaylist choosePlaylist) {
        this.choosePlaylist = choosePlaylist;
    }

    @Override
    public void makeAlbum(String name, ArrayList<Song> songs, ArrayList<SongGUI> songGUIS) {
        Album album = new Album(name, songs, songGUIS);
        AlbumGUI albumGUI = new AlbumGUI(album);
        albums.add(album);
        this.add(albumGUI);
        albumGUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                choosePlaylist.setAlbum(album);
                makeVisibilityTrue.makeTrue(0);
            }
        });
        this.add(albumGUI);
        revalidate();
    }

    @Override
    public void makeAlbumS(String name, Song song, SongGUI songGUI) {
        for (Album album : albums) {
            if (album.getName().equals(song.album)) {
                album.addSong(song, songGUI);
                flag=true;
                break;
            }
        }
        if(!flag) {
            Album album = new Album(name, song, songGUI);
            AlbumGUI albumGUI = new AlbumGUI(album);
            albums.add(album);
            this.add(albumGUI);
            albumGUI.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    choosePlaylist.setAlbum(album);
                    makeVisibilityTrue.makeTrue(0);
                }
            });
        }
        flag=false;
    }
}
