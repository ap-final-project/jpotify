import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Center extends Panel implements MakeVisibilityTrue,ChoosePlaylist{
    Label title;
    Scroll scrollpane;
    CenterSongs centerSongs;
    CenterPlayLists centerPlayLists;
    ArrayList<Playlist> playlists;
    CenterAlbum centerAlbum;
    public Center(CenterSongs centerSongs, CenterPlayLists centerPlayLists,ArrayList<Playlist> playlists,CenterAlbum centerAlbum) {
        super(2);
        this.centerAlbum=centerAlbum;
        this.playlists=playlists;
        this.centerPlayLists=centerPlayLists;
        this.centerSongs=centerSongs;
        this.setLayout(new BorderLayout());
        title=new Label("your songs",2);
        this.add(title,BorderLayout.PAGE_START);
        scrollpane=new Scroll(centerPlayLists);
        this.add(scrollpane);
        centerSongs.setVisible(true);
        CenterSongs.currentPlayList=playlists.get(0);
//        scrollpane.getViewport().revalidate();
        this.setVisible(true);
    }

    @Override
    public void makeTrue(int i) {
        switch (i){
            case 0:
                scrollpane.setPanel(centerSongs);
                centerSongs.setVisible(true);
                centerSongs.showPlayList(CenterSongs.currentPlayList);
                break;
            case 1:
                scrollpane.setPanel(centerPlayLists);
                centerPlayLists.setVisible(true);

                break;
            case 2:
                scrollpane.setPanel(centerAlbum);
                centerAlbum.setVisible(true);
                break;
        }
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        if (playlist==null) {
            playlist=playlists.get(0);
        }
        centerSongs.showPlayList(playlist);
    }

    @Override
    public void setAlbum(Album album) {
        centerSongs.showAlbum(album);
    }

}

