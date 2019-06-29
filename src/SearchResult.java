import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class SearchResult extends Panel implements Searching{
    Playlist recentlyPlayed;
    Label label=new Label("your search results:",3);
    Panel results=new Panel(2);
    public SearchResult(Playlist recentlyPlayed) {
        super(2);
        results.setLayout(new BoxLayout(results,1));
        this.recentlyPlayed=recentlyPlayed;
        this.add(label);
        this.add(results);
    }

    @Override
    public void search(String string) {
        results.removeAll();
        for (Song s:recentlyPlayed.songs) {
            if (insensitiveContainse(string,s.title)||insensitiveContainse(string,s.artist)||insensitiveContainse(string,s.album)){
                results.add(recentlyPlayed.getGUIBySong(s));
            }
        }
        results.add(Box.createVerticalGlue());
    }
    public boolean insensitiveContainse(String str,String source){
        return Pattern.compile(Pattern.quote(str), Pattern.CASE_INSENSITIVE).matcher(source).find();
    }
}
