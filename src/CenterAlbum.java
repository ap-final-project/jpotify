import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Albums that show at the center
 */
public class CenterAlbum extends Panel implements MakeAlbumListener {
    static ArrayList<Album> albums = new ArrayList<>();
    ArrayList<AlbumGUI> albumGuis = new ArrayList<>();
    ChoosePlaylist choosePlaylist = null;
    MakeVisibilityTrue makeVisibilityTrue = null;
    boolean flag = false;

    /**
     *
     * @param makeVisibilityTrue
     */
    public void setMakeVisibilityTrue(MakeVisibilityTrue makeVisibilityTrue) {
        this.makeVisibilityTrue = makeVisibilityTrue;
    }


    public CenterAlbum() {
        super(2);
        this.setLayout(new WrapLayout(WrapLayout.LEFT));
    }

    /**
     *
     * @param choosePlaylist
     */
    public void setChoosePlaylist(ChoosePlaylist choosePlaylist) {
        this.choosePlaylist = choosePlaylist;
    }

    /**
     *
     * @param name
     * @param songs
     * @param songGUIS
     */
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

    /**
     *
     * @param name
     * @param song
     * @param songGUI
     */
    @Override
    public void makeAlbumS(String name, Song song, SongGUI songGUI) {
        for (Album album : albums) {
            if (album.getName().equals(song.album)) {
                album.addSong(song, songGUI);
                album.getAlbumPL().songs.add(song);
                album.getAlbumPL().guis.add(songGUI);
                for (AlbumGUI albumGUI : albumGuis) {
                    if (albumGUI.album.equals(album))
                        albumGUI.label.setText("<html>" + "<div>" + album.getName() + "<br>" + album.getSongs().size() + " songs" + "</html>");
                    break;
                }
                flag = true;
                break;
            }
        }
        if (!flag) {
            Album album = new Album(name, song, songGUI);
            AlbumGUI albumGUI = new AlbumGUI(album);
            album.setMakeAlbumListener(this);
            albums.add(album);
            albumGuis.add(albumGUI);
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
        flag = false;
    }

    /**
     *
     * @param album
     */
    @Override
    public void removeAlbum(Album album) {

        for (Component c : this.getComponents()) {
            if (c instanceof AlbumGUI) {
                if (((AlbumGUI) c).album.equals(album)) {
                    remove(c);
                    albumGuis.remove(albums.indexOf(album));
                    albums.remove(album);
                }
            }
        }
    }
}
