import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.media.AudioTrack;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.*;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;

public class Song implements Runnable {
    AdvancedPlayer player;
    FileInputStream fileInputStream;
    File file;
    byte[] bArray;
    String title;
    String artist;
    String album;
    String year;
    String lenght;

    public Song(String path) throws JavaLayerException, IOException, InvalidDataException, UnsupportedTagException {

        file = new File(path);
        fileInputStream = new FileInputStream(file);
        Mp3File mp3file = new Mp3File(path);
        long time=mp3file.getLengthInSeconds();
        if(time%60>9)
        lenght=""+time/60+":"+time%60;
        else if(time%60<10 && time%60!=0)
            lenght=""+time/60+":"+0+time%60;
        else
            lenght=""+time/60+":"+00+time%60;

        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            byte[] imageData = id3v2Tag.getAlbumImage();
            if (imageData != null) {
                String mimeType = id3v2Tag.getAlbumImageMimeType();
                // Write image to file - can determine appropriate file extension from the mime type
                RandomAccessFile file = new RandomAccessFile("album-artwork", "rw");
                file.write(imageData);
                file.close();
            }
        }
        player = new AdvancedPlayer(fileInputStream);
        bArray = readFileToByteArray(file);
        String data = "";
        for (int i = 128; i > 1; i--) {
            data = data.concat(String.valueOf((char) bArray[bArray.length - i]));
        }
        title = this.getTitle(data);
        artist = this.getArtist(data);
        album = this.getAlbum(data);
        year = this.getYear(data);
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

    @Override
    public void run() {

    }
}