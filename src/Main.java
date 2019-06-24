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
        File tempFile = new File("data.txt");
        boolean exists = tempFile.exists();
        if (exists) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(tempFile));
            SaveInfo saved = (SaveInfo) objectInputStream.readObject();
            if (saved != null) {
                ArrayList<Song> recentlyPlayedAtLast= new ArrayList<>();
                for (String path : saved.songPaths) {
                    recentlyPlayedAtLast.add(new Song(path));
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
//                    System.out.println("play : " + p.name);
                    ArrayList<Integer> indexes = new ArrayList<>();
                    for (int i=0;i<recentlyPlayedAtLast.size();i++) {
                            if (saved.playlistsAndItsPositiom.get(i).containsKey(p.name)) {
//                                System.out.println(recentlyPlayedAtLast.get(i).title+" is in "+p.name);
                                p.add(new SongGUI(recentlyPlayedAtLast.get(i)), recentlyPlayedAtLast.get(i));
                                indexes.add(saved.playlistsAndItsPositiom.get(i).get(p.name));
                            }
                    }
//                    for (int i = 0; i < p.songs.size(); i++) {
//                        for (int j = 0; j < p.songs.size() - 1; j++) {
//                            if (indexs.get(j) > indexs.get(j + 1)) {
//                                Integer t = indexs.get(j);
//                                indexs.set(j, indexs.get(j + 1));
//                                indexs.set(j + 1, t);
//                                Song temp = p.songs.get(j);
//                                p.songs.set(j, p.songs.get(j + 1));
//                                p.songs.set(j + 1, temp);
//                            }
//                        }
//                    }
                System.out.println(p.name+" contains :");
                for (int i=0;i<p.songs.size();i++){
                    System.out.println(" "+p.songs.get(i).title);
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

