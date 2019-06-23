import java.awt.*;
import java.util.ArrayList;

public class AlbumGUI extends Panel{
    Label name;
    Label artist;
    Label num;
    Album album;
    Label picture;
    public AlbumGUI(Album album){
        super(3);
        this.album=album;
        artist=new Label(album.getSongs().get(0).artist,3);
        num=new Label(String.valueOf(album.getSongs().size()),3);
        name=new Label(album.getName(),3);
//        for (int i = 0; i <album.getNum() ; i++) {
            picture=album.getSongs().get(0).artWork;
//        }
        Panel description=new Panel(3);
        description.setLayout(new GridLayout(2,2));
        description.add(name);
        description.add(num);
        description.add(artist);
        this.setLayout(new GridLayout(2,1));
        this.add(picture);
        this.add(description);
    }
}
