import javax.print.attribute.standard.OutputDeviceAssigned;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ClientSender  {
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private String command;
    public ClientSender(){
    }
    public void songChanged(String title, String artist, String IP) throws IOException {
        dataOutputStream.writeUTF("songChanged");
        dataOutputStream.writeUTF(title);
        dataOutputStream.writeUTF(artist);
    }
    public void getSong(String IP) throws IOException {
        dataOutputStream.writeUTF("getSong");
        dataOutputStream.writeUTF(IP);
    }

    public void addFriend(String ip) throws IOException {
        dataOutputStream.writeUTF("addFriend");
        dataOutputStream.writeUTF(ip);
    }
    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
    this.dataOutputStream=dataOutputStream;
    }
}
