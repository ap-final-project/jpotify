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
//    public OutputStream getOutputStream() {
//        return outputStream;
//    }

//    public void setOutputStream(OutputStream outputStream) {
//        this.outputStream = outputStream;
//    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
    this.dataOutputStream=dataOutputStream;
    }

    public void setServerUpdate(ServerUpdate serverUpdate) {
        this.serverUpdate = serverUpdate;
    }

//    public InputStream getInputStream() {
//        return inputStream;
//    }

//    public void setInputStream(InputStream inputStream) {
//        this.inputStream = inputStream;
//    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
