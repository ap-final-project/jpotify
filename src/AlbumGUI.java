import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class AlbumGUI extends Panel implements PanelGuiInformer{
    Album album;
    Label picture;
    Label label;

    public AlbumGUI(Album album) {
        super(3);
        this.album = album;
        label=new Label(3);
        label.setText("<html>"+"<div>"+album.getName()+album.getSongs().size()+" songs"+album.getArtist()+"</div>"+"</html>");
        this.setPreferredSize(new Dimension(200+this.label.getWidth(),200+this.label.getHeight()));
        picture = new Label(3);
        picture.setIcon(album.getSongs().get(0).imageIcon);
        this.setLayout(new BorderLayout());
        label.setBorder(BorderFactory.createMatteBorder(2,0,0,0,Color.GRAY));
        this.add(picture,BorderLayout.CENTER);
        this.add(label,BorderLayout.PAGE_END);
        this.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
    }

    @Override
    public void updateGui() {
        label.setText("<html>"+"<div>"+album.getName()+album.getNum()+" songs"+album.getSongs().size()+"</div>"+"</html>");
    }
}
