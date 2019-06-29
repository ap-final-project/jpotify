import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MovieLibrary extends Panel implements AddMovie {
    private ArrayList<MovieGui> guis;
    private Panel list;
    private MovieBarListener movieBarListener;
    private MakeVisibilityTrue makeVisibilityTrue;
    public MovieLibrary() {
        super(2);
        this.setLayout(new GridLayout(0,1));
        guis=new ArrayList<>();
        list=new Panel(2);
        this.add(list);
    }

    @Override
    public void addMovie(String Path) {
        MovieGui movieGui=new MovieGui(Path);
        movieGui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makeVisibilityTrue.makeTrue(6);
                movieBarListener.play(Path);
            }
        });
        guis.add(movieGui);
        list.add(movieGui);
    }

    public void setMovieBarListener(MovieBarListener movieBarListener) {
        this.movieBarListener = movieBarListener;
    }

    public void setMakeVisibilityTrue(MakeVisibilityTrue makeVisibilityTrue) {
        this.makeVisibilityTrue = makeVisibilityTrue;
    }
}
