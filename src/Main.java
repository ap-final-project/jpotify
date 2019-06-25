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
        ArrayList<Playlist> playlists = null;
        playlists = new ArrayList<>();
        Playlist recentlyPlayedAtLast= new Playlist("recentlyPlayed","All your Songs","img\\pink-gramaphone.jpg");
        Playlist favorites= new Playlist("favorites","you liked Songs","img\\favoriteCover.png");
        playlists.add(recentlyPlayedAtLast);
        playlists.add(favorites);
        File tempFile = new File("data.txt");
        boolean exists = tempFile.exists();
        if (exists) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(tempFile));
            SaveInfo saved = (SaveInfo) objectInputStream.readObject();
            if (saved != null) {
                for (String path : saved.songPaths) {
                    Song song=new Song(path);
                    recentlyPlayedAtLast.add(new SongGUI(song),song);
                }
                for (int i = 2; i < saved.playlistName.size(); i++) {
                    String name = saved.playlistName.get(i);
                    String description = saved.playlistDescription.get(i);
                    String imgPath = saved.playlistImgPath.get(i);
                    playlists.add(new Playlist(name, description, imgPath));
                }
                for (Playlist p : playlists) {
                    if (p.equals(recentlyPlayedAtLast)) continue;
                    ArrayList<Integer> indexes = new ArrayList<>();
                    int size=recentlyPlayedAtLast.songs.size();
                    for (int i=0;i<size;i++) {
                        System.out.println("hashmap!?"+i);
                        if (saved.playlistsAndItsPositiom.get(i).containsKey(p.name)) {
                                p.add(new SongGUI(recentlyPlayedAtLast.songs.get(i)), recentlyPlayedAtLast.songs.get(i));
                                indexes.add(saved.playlistsAndItsPositiom.get(i).get(p.name));
                            }
                    }
                    int pSize=p.songs.size();
                    for (int i = 0; i <pSize; i++) {
                        for (int j = 0; j < pSize - 1; j++) {
                            if (indexes.get(j) > indexes.get(j + 1)) {
                                Integer t = indexes.get(j);
                                indexes.set(j, indexes.get(j + 1));
                                indexes.set(j + 1, t);
                                Song temp = p.songs.get(j);
                                p.songs.set(j, p.songs.get(j + 1));
                                p.songs.set(j + 1, temp);
                            }
                        }
                    }
                for (int i=0;i<p.songs.size();i++){
                    System.out.println(" "+p.songs.get(i).title);
                }
                }
            }
        }

        MainPage mainPage = new MainPage(playlists);

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
                    Song current=mainPage.musicPlayer.recentlyPlayed.songs.get(i);
                    HashMap<String, Integer> songPlAndLocation = new HashMap<>();
                    for (Playlist p : mainPage.centerPlayLists.playlists) {
                        if (p.songs.contains(current)) {
                            songPlAndLocation.put(p.name , p.songs.indexOf(mainPage.musicPlayer.recentlyPlayed.songs.get(i)));
                            System.out.println("we put"+mainPage.musicPlayer.recentlyPlayed.songs.get(i).title+"in"+p.name);
                        }
                    }
                    saveInfo.playlistsAndItsPositiom.add(songPlAndLocation);
                }

                for (int i=0;i<mainPage.musicPlayer.recentlyPlayed.songs.size();i++){
                    System.out.println(mainPage.musicPlayer.recentlyPlayed.songs.get(i).title);
                        for (Map.Entry<String,Integer> entry:saveInfo.playlistsAndItsPositiom.get(i).entrySet()) {
                            System.out.println("  is"+entry.getValue()+" om in "+entry.getKey());
                        }
                }
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("data.txt")));
                    objectOutputStream.writeObject(saveInfo);
                    System.out.println("songs num"+saveInfo.songPaths.size());
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

