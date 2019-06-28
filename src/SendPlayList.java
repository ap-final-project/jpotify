import java.io.*;
import java.util.ArrayList;

public class SendPlayList implements Serializable{
ArrayList<byte[]> sharedSongs=new ArrayList<>();

    public void getPlayList(Playlist pLayList) throws IOException {
        for (Song song:pLayList.songs) {
            File file=new File(song.path);
            FileInputStream fileInputStream=new FileInputStream(file);
            byte[] s=new byte[(int)file.length()];
            fileInputStream.read(s);
            sharedSongs.add(s);
        }
    }
}
