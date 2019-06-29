import java.awt.*;

public class Bar extends Panel implements changeBar {
    MusicBar musicBar;
    MovieBar movieBar;

   public Bar(MovieBar movieBar,MusicBar musicBar){
    super(2);
    this.setLayout(new GridLayout(1,1));
    this.movieBar=movieBar;
    this.musicBar=musicBar;
    this.add(musicBar);
    musicBar.setVisible(true);
   }
    @Override
    public void setMovieBar() {
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
