import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;

import javax.swing.*;
import java.awt.*;

/**
 * class for finding lyric of music
 */
public class Lyrics extends JFrame {
    String trackName;
    String artistName;
    public Lyrics(String trackName,String artistName){
        this.setVisible(true);
        this.setMinimumSize(new Dimension(400,1000));
        this.setMaximumSize(new Dimension(400,1000));
        this.setLayout(new BorderLayout());
        this.trackName=trackName;
        this.artistName=artistName;
        String apiKey = "5c816ea1678c68472364e0d23f9e4302";
        Label label;
        MusixMatch musixMatch = new MusixMatch(apiKey);
        Track track = null;
        try {
            track = musixMatch.getMatchingTrack(trackName, artistName);
            TrackData data = track.getTrack();
            int trackID = data.getTrackId();
            org.jmusixmatch.entity.lyrics.Lyrics lyrics = musixMatch.getLyrics(trackID);
            label=new Label(3);
            label.setText("<html>" +trackName+" from "+artistName+" lyric : "+"<br>"+"<br>"+ lyrics.getLyricsBody().replaceAll("\n", "<br/>") + "</html>");
            label.setFont(new Font("Cambria", Font.BOLD, 17));
            this.add(label,BorderLayout.CENTER);
        } catch (MusixMatchException e) {
            label=new Label(3);
            label.setFont(new Font("Cambria", Font.BOLD, 17));
            label.setText("SONG's LYRIC NOT FOUND :(");
        }
    }
}