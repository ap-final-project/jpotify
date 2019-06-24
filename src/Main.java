import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {

//        WelcomeJFrame welcome=new WelcomeJFrame();
//        welcome.setVisible(true);
//        Thread.sleep(4000);
//        welcome.setVisible(false);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("data.bin")));
        SaveInfo saved = (SaveInfo) objectInputStream.readObject();
        System.out.println(saved.songPaths);
        Playlist recentlyPlayedAtLast = new Playlist("recentlyPlayed", "All your Songs", "play.png");
        for (String path : saved.songPaths) {
            recentlyPlayedAtLast.songs.add(new Song(path));
        }
        ArrayList<Playlist> playlists = new ArrayList<>();
        for (Map.Entry<String, String[]> playlist : saved.playlistInfo.entrySet()) {
            playlists.add(new Playlist(playlist.getKey(), playlist.getValue()[1], playlist.getValue()[0]));
        }
        for (Playlist p : playlists) {
            ArrayList<Integer> indexs=new ArrayList<>();
            for (Song s : recentlyPlayedAtLast.songs) {
                if (saved.playlistsAndItsPositiom.get(recentlyPlayedAtLast.songs.indexOf(s)).containsKey(p)){
                    p.add(new SongGUI(s),s);
                    indexs.add(saved.playlistsAndItsPositiom.get(recentlyPlayedAtLast.songs.indexOf(s)).get(p));
                }
            }
        }
        MainPage mainPage = new MainPage();

        mainPage.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                SaveInfo saveInfo = new SaveInfo();
                for (Song song : mainPage.musicPlayer.recentlyPlayed.songs) {
                    saveInfo.songPaths.add(song.getPath());
                }
                for (Playlist p : mainPage.musicPlayer.playlists) {
                    String[] info = new String[2];
                    info[0] = p.imgPath;
                    info[1] = p.description;
                    saveInfo.playlistInfo.put(p.name, info);
                }
                for (Song s : mainPage.musicPlayer.recentlyPlayed.songs) {
                    HashMap<String, Integer> songPlAndLocation = new HashMap<>();
                    for (Playlist p : mainPage.centerPlayLists.playlists) {
                        if (p.songs.contains(s)) {
                            songPlAndLocation.put(p.name, p.songs.indexOf(s));
                        }
                        saveInfo.playlistsAndItsPositiom.add(songPlAndLocation);
                    }
                }
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("data.bin")));
                    objectOutputStream.writeObject(saveInfo);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

