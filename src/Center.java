import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Gui for the center of the program
 */
public class Center extends Panel implements MakeVisibilityTrue,ChoosePlaylist{
    private Scroll scrollpane;
    private CenterSongs centerSongs;
    private CenterPlayLists centerPlayLists;
    private ArrayList<Playlist> playlists;
    private CenterAlbum centerAlbum;
    private Movie movie;
    private MovieLibrary movieLibrary;
    private changeBar changeBar;
    SearchResult searchResult;

    /**
     *
     * @param searchResult
     * @param movieLibrary
     * @param movie
     * @param centerSongs
     * @param centerPlayLists
     * @param playlists
     * @param centerAlbum
     */
    public Center(SearchResult searchResult,MovieLibrary movieLibrary,Movie movie,CenterSongs centerSongs, CenterPlayLists centerPlayLists,ArrayList<Playlist> playlists,CenterAlbum centerAlbum) {
        super(2);
        this.movie=movie;
        this.searchResult=searchResult;
        this.movieLibrary=movieLibrary;
        this.centerAlbum=centerAlbum;
        this.playlists=playlists;
        this.centerPlayLists=centerPlayLists;
        this.centerSongs=centerSongs;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(18,18,18));
        scrollpane=new Scroll(centerPlayLists);
        this.add(scrollpane);
        centerSongs.setVisible(true);
        CenterSongs.currentPlayList=playlists.get(0);
        scrollpane.getViewport().revalidate();
        this.setVisible(true);
    }

    /**
     *
     * @param i
     */
    @Override
    public void makeTrue(int i) {
        switch (i){
            case 0:
                scrollpane.setPanel(centerSongs);
                centerSongs.setVisible(true);
                changeBar.setMusicBar();
                break;
            case 1:
                scrollpane.setPanel(centerPlayLists);
                centerPlayLists.setVisible(true);
                changeBar.setMusicBar();
                break;
            case 2:
                scrollpane.setPanel(centerAlbum);
                centerAlbum.setVisible(true);
                changeBar.setMusicBar();
                break;
            case 4:
                scrollpane.setPanel(centerSongs);
                centerSongs.setVisible(true);
                centerSongs.showPlayList(CenterSongs.currentPlayList);
                changeBar.setMusicBar();
                break;
            case 5:
                scrollpane.setPanel(movieLibrary);
                movieLibrary.setVisible(true);
                changeBar.setMovieBar();
                break;
            case 6:
                scrollpane.setPanel(movie);
                movie.setVisible(true);
                changeBar.setMovieBar();
                break;
            case 7:
                scrollpane.setPanel(searchResult);
                changeBar.setMusicBar();
                break;
        }
    }

    /**
     *
     * @param playlist
     */
    @Override
    public void setPlaylist(Playlist playlist) {
        if (playlist==null) {
            playlist=playlists.get(0);
        }
        centerSongs.showPlayList(playlist);
    }

    /**
     *
     * @param album
     */
    @Override
    public void setAlbum(Album album) {
        centerSongs.showAlbum(album);
    }

    /**
     *
     * @param changeBar
     */
    public void setChangeBar(changeBar changeBar) {
        this.changeBar = changeBar;
    }
}

