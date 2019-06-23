import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CenterPlayLists extends Panel implements AddPlaylistListener {
    ArrayList<PLGUI> playlistGUIs=new ArrayList<>();
    MakeVisibilityTrue makeVisibilityTrue=null;
    ChoosePlaylist choosePlaylistListener=null;
    ArrayList<Playlist> playlists;
    public void setChoosePlaylistListener(ChoosePlaylist choosePlaylistListener) {
        this.choosePlaylistListener = choosePlaylistListener;
    }

    public void setMakeVisibilityTrue(MakeVisibilityTrue makeVisibilityTrue) {
        this.makeVisibilityTrue = makeVisibilityTrue;
    }

    public CenterPlayLists(ArrayList<Playlist> playlists) {
        super(1);
        this.playlists=playlists;
        this.setLayout(new FlowLayout());
        PLGUI favGUI=new PLGUI(playlists.get(1),playlists.get(1).name,playlists.get(1).imgPath);
        favGUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                choosePlaylistListener.setPlaylist(playlists.get(1));
                makeVisibilityTrue.makeTrue(0);
            }
        });
        this.add(favGUI);
        revalidate();
    }

    @Override
    public void makePlayList(String name, String path) {
        Playlist playlist=new Playlist(name,path);
        PLGUI plgui=new PLGUI(playlist,name,path);
        playlists.add(playlist);
        playlistGUIs.add(plgui);
        plgui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                choosePlaylistListener.setPlaylist(plgui.getPlaylist());
                makeVisibilityTrue.makeTrue(0);
            }
        });
        this.add(plgui);
        revalidate();
    }
}
