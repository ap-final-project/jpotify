import java.awt.*;

public class Bar extends Panel implements changeBar {
    MusicBar musicBar;
    MovieBar movieBar;

   public Bar(MovieBar movieBar,MusicBar musicBar){
    super(2);
    this.setLayout(new GridLayout(1,1));
    this.movieBar=movieBar;
    movieBar.setBackground(Color.GREEN);
    this.musicBar=musicBar;
    this.add(musicBar);
    musicBar.setVisible(true);
   }
    @Override
    public void setMovieBar() {
        System.out.println("changing to movie");
        this.remove(musicBar);
        this.add(movieBar);
        movieBar.setVisible(true);
        revalidate();
   }

    @Override
    public void setMusicBar() {
       this.remove(movieBar);
       this.add(musicBar);
       musicBar.setVisible(true);
    revalidate();
   }
}
