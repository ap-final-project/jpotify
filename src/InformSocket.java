import java.io.IOException;

public interface InformSocket {
 void getSong(String IP) throws IOException;
 void changeSong(String title,String artist) throws IOException;
}
