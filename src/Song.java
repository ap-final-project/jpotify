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

public class Song {
    Player player;
    String path = "";
    FileInputStream fileInputStream;
    File file;
    byte[] bArray;

    public Song() throws JavaLayerException, FileNotFoundException {
        path = "music1.mp3";
        file = new File(path);
        fileInputStream = new FileInputStream(path);
        player = new Player(fileInputStream);
        bArray = readFileToByteArray(file);


//        for (int i = bArray.length-256; i < bArray.length; i++){
//            System.out.println((char) bArray[i]);
//        }
//        ino khodam zadam :
        for (int i =1; i < 128; i++){
            System.out.println((char)bArray[bArray.length-i]);
        }
    }
    private static byte[] readFileToByteArray(File file){
        FileInputStream fis = null;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();

        }catch(IOException ioExp){
            ioExp.printStackTrace();
        }
        return bArray;
    }
}
