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
    }


    @Override
    public void makePlayList(String name, String path) {
        Playlist playlist=new Playlist(name,path);
        PLGUI plgui=new PLGUI(playlist,name,path);
        playlists.add(playlist);
        playlistGUIs.add(plgui);
        System.out.println("injaaaaaaaaaaaaaaaaaaaaaaa");
//        for (SongGUI songGUI:playlist.guis) {
//            System.out.println(playlist.guis.size());
//            addPlSongs.addGui(songGUI);
//        }
        plgui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                currentPlaylist=plgui.playlist;
                choosePlaylistListener.setPlaylist(plgui.getPlaylist());
                makeVisibilityTrue.makeTrue(0);
            }
        });
        this.add(plgui);
        revalidate();
    }
}
