import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URL;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import static javafx.scene.input.KeyCode.T;
//import static jdk.vm.ci.sparc.SPARC.o1;
//import static jdk.vm.ci.sparc.SPARC.o2;

public class Main {
    static ArrayList<User> users=new ArrayList<>();
    static String userName = "";
    static ArrayList<Playlist> playlists = null;
    static User me;
    static boolean flag=true;
    public static void main(String[] args) throws Exception {
//        WelcomeJFrame welcome=new WelcomeJFrame();
//        welcome.setVisible(true);
//        Thread.sleep(4000);
//        welcome.setVisible(false);
        File tempF=new File("users.txt");
        boolean existsF=tempF.exists();
        UsersInfo usersInfo;
        Login loginPage = new Login();
        if(existsF){
            ObjectInputStream userInputStream = new ObjectInputStream(new FileInputStream(tempF));
            usersInfo = (UsersInfo) userInputStream.readObject();
            for (User user:usersInfo.users ) {
                users.add(user);
            }
        }
        else {
            System.out.println("users nadarim");
            usersInfo = new UsersInfo();
        }
        String[] imgPath1=new String[1];
        loginPage.photo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnValue = jfc.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    imgPath1[0] = selectedFile.getAbsolutePath();
                }
            }
        });
        loginPage.signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid=true;
                System.out.println("userrrrrrrs : "+users.size());
                for (User user:users) {
                    System.out.println(user.getName());
                    if(user.getName().equals(loginPage.userText.getText())){
                        System.out.println("noooooooooooooooooooooo");
                        valid=false;
                    }
                }
                if(valid){
                loginPage.setVisible(false);
                me = new User(loginPage.userText.getText(), loginPage.passText.getText(),imgPath1[0]);
//                    me.setImgPath(imgPath1[0]);
                    System.out.println("signiiiiiiiiiiiiiiiiing upppp");
                    flag=false;
                    users.add(me);
                userName = loginPage.userName;
            }
            }
        });
        loginPage.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (User user : usersInfo.users) {
                    if (user.getName().equals(loginPage.userText.getText()) && user.getPassword().equals(loginPage.passText.getText())) {
                        me=new User(user.getName(),user.getPassword(),user.getImgPath());
                        me.setFriendsIP(user.getFriendsIPs());
                        me.setFriends(user.getFriends());
                        me.setIP(user.getIP());
                        me.setImg(user.getImg());
//                        me.setImgPath(user.getImgPath());
                        userName = loginPage.userName;
                        users.add(me);
                        System.out.println("yaftamat");
                        flag=false;
                        loginPage.setVisible(false);
                    }
                }
            }
        });
        while (flag){
//            System.out.print("hi");
            System.out.print("");
        }
        System.out.println("ouuuuuuuuuuuuuuuuuuuuuuut");
        playlists = new ArrayList<>();
        Playlist recentlyPlayedAtLast= new Playlist("recentlyPlayed","All your Songs","img\\pink-gramaphone.jpg");
        Playlist favorites= new Playlist("favorites","you liked Songs","img\\favoriteCover.png");
        playlists.add(recentlyPlayedAtLast);
        playlists.add(favorites);
        File tempFile = new File(me.getName()+".bin");
        boolean exists = tempFile.exists();
        if (exists) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(tempFile));
            SaveInfo saved = (SaveInfo) objectInputStream.readObject();
            if (saved != null) {
                for (String path : saved.songPaths) {
                    Song song=new Song(path);
                    SongGUI songGUI=new SongGUI(song);
                    recentlyPlayedAtLast.add(songGUI,song);
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
//                        System.out.println("hashmap!?"+i);
                        if (saved.playlistsAndItsPositiom.get(i).containsKey(p.name)) {
                                p.add((recentlyPlayedAtLast.guis.get(i)), recentlyPlayedAtLast.songs.get(i));
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

//        User user=new User("dovomi" ,"1234");
            MainPage mainPage = new MainPage(playlists,me);

        mainPage.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                SaveInfo saveInfo = new SaveInfo();
                UsersInfo usersInfo=new UsersInfo();
                for (User user:users) {
                    usersInfo.users.add(user);

                }
                System.out.println("saving "+ usersInfo.users.size()+"users");
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
                    ObjectOutputStream usersOutputStream=new ObjectOutputStream(new FileOutputStream(new File("users.txt")));
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(me.getName()+".bin")));
                    usersOutputStream.writeObject(usersInfo);
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

