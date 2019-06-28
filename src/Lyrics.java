import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;

import javax.swing.*;
import java.awt.*;

public class Lyrics extends JFrame {
    String trackName;
    String artistName;
    public Lyrics(String trackName,String artistName) throws MusixMatchException {
        this.trackName=trackName;
        this.artistName=artistName;
        String apiKey = "5c816ea1678c68472364e0d23f9e4302";
        MusixMatch musixMatch = new MusixMatch(apiKey);
        // Track Search [ Fuzzy ]
        Track track = musixMatch.getMatchingTrack(trackName, artistName);
        TrackData data = track.getTrack();
        int trackID = data.getTrackId();
        org.jmusixmatch.entity.lyrics.Lyrics lyrics = musixMatch.getLyrics(trackID);
        this.setMinimumSize(new Dimension(400,1000));
        this.setMaximumSize(new Dimension(400,1000));

//
//        System.out.println("AlbumID : " + data.getAlbumId());
//        System.out.println("Album Name : " + data.getAlbumName());
//        System.out.println("Artist ID : " + data.getArtistId());
//        System.out.println("Album Name : " + data.getArtistName());
//        System.out.println("Track ID : " + data.getTrackId());
//
//
//        System.out.println("Lyrics ID       : " + lyrics.getLyricsId());
//        System.out.println("Lyrics Language : " + lyrics.getLyricsLang());
//        System.out.println("Lyrics Body     : " + lyrics.getLyricsBody());
//        System.out.println("Script-Tracking-URL : " + lyrics.getScriptTrackingURL());
//        System.out.println("Pixel-Tracking-URL : " + lyrics.getPixelTrackingURL());
//        System.out.println("Lyrics Copyright : " + lyrics.getLyricsCopyright());
            this.setVisible(true);
            Label label=new Label(3);
        label.setText("<html>" + lyrics.getLyricsBody().replaceAll("\n", "<br/>") + "</html>");
        this.add(label);

    }

//    public static void main(String[] args) throws MusixMatchException {
//        Lyrics l=new Lyrics();
//    }
}