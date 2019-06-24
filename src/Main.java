import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URL;
import java.util.*;

import javax.swing.*;

import static javafx.scene.input.KeyCode.T;
//import static jdk.vm.ci.sparc.SPARC.o1;
//import static jdk.vm.ci.sparc.SPARC.o2;

public class Main {
    public static void main(String[] args) throws Exception {

//        WelcomeJFrame welcome=new WelcomeJFrame();
//        welcome.setVisible(true);
//        Thread.sleep(4000);
//        welcome.setVisible(false);
        File tempFile = new File("data.bin");
        boolean exists = tempFile.exists();
        if (exists) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(tempFile));
            SaveInfo saved = (SaveInfo) objectInputStream.readObject();
            if (saved != null) {
                Playlist recentlyPlayedAtLast = new Playlist("recentlyPlayed2", "All your Songs", "play.png");
                for (String path : saved.songPaths) {
                    recentlyPlayedAtLast.songs.add(new Song(path));
                }
                ArrayList<Playlist> playlists = new ArrayList<>();
                for (int i = 0; i < saved.playlistName.size(); i++) {
//                    int sign=p.getKey().indexOf('|');
//                    String name=p.getKey().substring(0,sign);
                    String name = saved.playlistName.get(i);
                    String description = saved.playlistDescription.get(i);
                    String imgPath = saved.playlistImgPath.get(i);
                    System.out.println("name p :"+name);
                    System.out.println("des p :"+description);
                    System.out.println("path p :"+imgPath);
                    playlists.add(new Playlist(name, description, imgPath));
                }
                for (Playlist p : playlists) {
                    System.out.println("play : " + p.name);
                    ArrayList<Integer> indexs = new ArrayList<>();
                    for (int i=0;i<recentlyPlayedAtLast.songs.size();i++) {
//                        for (Map.Entry<String,Integer> entry  : saved.playlistsAndItsPositiom.get(i).entrySet()) {
                            if (saved.playlistsAndItsPositiom.get(i).get(p.name)!=null) {
//                                System.out.println(entry.getKey()+"=="+p.name);
                                System.out.println(recentlyPlayedAtLast.songs.get(i).title+" is in "+p.name);
                                p.add(new SongGUI(recentlyPlayedAtLast.songs.get(i)), recentlyPlayedAtLast.songs.get(i));
                                indexs.add(saved.playlistsAndItsPositiom.get(i).get(p.name));
                            }

                    }
                    for (int i = 0; i < p.songs.size(); i++) {
                        for (int j = 0; j < p.songs.size() - 1; j++) {
                            if (indexs.get(j) > indexs.get(j + 1)) {
                                Integer t = indexs.get(j);
                                indexs.set(j, indexs.get(j + 1));
                                indexs.set(j + 1, t);
                                Song temp = p.songs.get(j);
                                p.songs.set(j, p.songs.get(j + 1));
                                p.songs.set(j + 1, temp);
                            }
                        }
                    }

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
                    saveInfo.playlistName.add(p.name);
                    saveInfo.playlistImgPath.add(p.imgPath);
                    saveInfo.playlistDescription.add(p.description);
                }
                for (int i=0;i<mainPage.musicPlayer.recentlyPlayed.songs.size();i++) {
                    HashMap<String, Integer> songPlAndLocation = new HashMap<>();
                    for (Playlist p : mainPage.centerPlayLists.playlists) {
                        if (p.songs.contains(mainPage.musicPlayer.recentlyPlayed.songs.get(i))) {
                            songPlAndLocation.put(p.name , i);
//                            System.out.println("we put"+mainPage.musicPlayer.recentlyPlayed.songs.get(i).title+"in"+p.name);
                        }
                        saveInfo.playlistsAndItsPositiom.add(songPlAndLocation);
                    }
                }
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("data.bin")));
                    objectOutputStream.writeObject(saveInfo);

                    for (int i = 0; i <saveInfo.playlistsAndItsPositiom.size() ; i++) {
                        objectOutputStream.writeObject(saveInfo.playlistsAndItsPositiom.get(i));
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

