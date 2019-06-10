import javafx.scene.media.AudioTrack;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.*;

import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class Song {
    Player player;
    FileInputStream fileInputStream;
    File file;
    byte[] bArray;
    String title;
    String artist;
    String album;
    String year;
    public Song(String path) throws JavaLayerException, FileNotFoundException {
        file = new File(path);
        fileInputStream = new FileInputStream(file);
        player = new Player(fileInputStream);
        bArray = readFileToByteArray(file);
        String data = "";
        for (int i = 128; i > 1; i--) {
            data = data.concat(String.valueOf((char) bArray[bArray.length - i]));
        }
        title = this.getTitle(data);
//        System.out.println("title : " + title);
        artist = this.getArtist(data);
//        System.out.println("Artist : " + artist);
        album = this.getAlbum(data);
//        System.out.println("Album : " + album);
        year = this.getYear(data);
//        System.out.println("Year : "+year);
    }

    private String getYear(String data) {
        String year="";
        year=data.substring(93,97);
        return year;
    }

    private String getTitle(String data) {
//        System.out.println(data);
        String title;
        title = data.substring(3, 33);
        return title;
    }

    private String getArtist(String data) {
        String artist = "";
        artist = data.substring(33, 63);
        return artist;
    }

    private String getAlbum(String data) {
        String album = "";
        album = data.substring(63, 93);
        return album;
    }

    private static byte[] readFileToByteArray(File file) {
        FileInputStream fis = null;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();

        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        }
        return bArray;
    }
}