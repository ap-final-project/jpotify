import javax.print.attribute.standard.OutputDeviceAssigned;
import java.io.*;

/**
 * For sending data to server.
 */
public class ClientSender  {
//    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
//    private InputStream inputStream;
    private ServerUpdate serverUpdate=null;
    private DataInputStream dataInputStream;
    private String command;
    public ClientSender(){
    }

    /**
     *
     * @param title
     * @param artist
     * @param IP
     * @throws IOException
     */
    public void songChanged(String title, String artist, String IP) throws IOException {
        dataOutputStream.writeUTF("songChanged");
        dataOutputStream.writeUTF(title);
        dataOutputStream.writeUTF(artist);
        dataOutputStream.flush();
    }

    /**
     *
     * @param IP
     * @throws IOException
     */
    public void getSong(String IP) throws IOException {
        dataOutputStream.writeUTF("getSong");
        dataOutputStream.writeUTF(IP);
        dataOutputStream.flush();
    }

    /**
     *
     * @param ip
     * @throws IOException
     */
    public void addFriend(String ip) throws IOException {
        dataOutputStream.writeUTF("addFriend");
        dataOutputStream.writeUTF(ip);
        dataOutputStream.flush();
        }

    /**
     *
     * @param ip
     * @throws IOException
     */
    public void getPL(String ip) throws IOException {
        dataOutputStream.writeUTF("getPlaylist");
        dataOutputStream.writeUTF(ip);
        dataOutputStream.flush();
    }

    /**
     *
     * @param dataOutputStream
     */
    public void setDataOutputStream(DataOutputStream dataOutputStream) {
    this.dataOutputStream=dataOutputStream;
    }

    /**
     *
     * @param serverUpdate
     */
    public void setServerUpdate(ServerUpdate serverUpdate) {
        this.serverUpdate = serverUpdate;
    }

    /**
     *
     * @return dataOutputStream
     */
    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    /**
     *
     * @param dataInputStream
     */
    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }
}
