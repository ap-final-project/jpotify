import javax.print.attribute.standard.OutputDeviceAssigned;
import java.io.*;

public class ClientSender  {
//    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
//    private InputStream inputStream;
    private ServerUpdate serverUpdate=null;
    private DataInputStream dataInputStream;
    private String command;
    public ClientSender(){
    }
    public void songChanged(String title, String artist, String IP) throws IOException {
        dataOutputStream.writeUTF("songChanged");
        dataOutputStream.writeUTF(title);
        dataOutputStream.writeUTF(artist);
        dataOutputStream.flush();
    }
    public void getSong(String IP) throws IOException {
        dataOutputStream.writeUTF("getSong");
        dataOutputStream.writeUTF(IP);
        dataOutputStream.flush();
    }

    public void addFriend(String ip) throws IOException {
        dataOutputStream.writeUTF("addFriend");
        dataOutputStream.writeUTF(ip);
        dataOutputStream.flush();
        }

    public void getPL(String ip) throws IOException {
        System.out.println("daram miram begiram playlistesho");
        dataOutputStream.writeUTF("getPlaylist");
        dataOutputStream.writeUTF(ip);
        dataOutputStream.flush();
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
    this.dataOutputStream=dataOutputStream;
    }

    public void setServerUpdate(ServerUpdate serverUpdate) {
        this.serverUpdate = serverUpdate;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }
}
