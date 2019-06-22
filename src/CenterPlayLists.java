import java.awt.*;

public class CenterPlayLists extends Panel implements addPlGUIToCenter {
    public CenterPlayLists() {
        super(1);
        this.setLayout(new FlowLayout());
    }

    @Override
    public void makePlayList(PLGUI plgui2) {
        this.add(plgui2);
        revalidate();
    }
}
