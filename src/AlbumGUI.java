import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.util.ArrayList;

public class AlbumGUI extends Panel {
    Album album;
    Label picture;
    Label label;

    public AlbumGUI(Album album) {
        super(3);
        this.album = album;
        label=new Label("<html>"+album.getName()+"<br>"+album.getNum()+" songs"+"<br>"+album.getArtist()+"</html>",3);
        picture = new Label(3);
        picture.setIcon(album.getSongs().get(0).imageIcon);
        this.setLayout(new BorderLayout());
        label.setBorder(BorderFactory.createMatteBorder(2,0,0,0,Color.GRAY));
        this.add(picture,BorderLayout.CENTER);
        this.add(label,BorderLayout.PAGE_END);
        this.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
//        this.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED,Color.DARK_GRAY,Color.MAGENTA));
    }
}
