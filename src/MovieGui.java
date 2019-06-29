import java.awt.*;

public class MovieGui extends Panel {
    String path;
    String title;
    Label Info;

    public MovieGui(String path) {
        super(2);
        this.path=path;
        Info=new Label(2);
        Info.setPreferredSize(new Dimension(300,50));
        Info.setText("movie:"+path);
        this.add(Info);
    }
}
