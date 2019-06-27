import java.io.*;


public class ClientReceiver implements Runnable {
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private ServerUpdate serverUpdate = null;

    public ClientReceiver() {
    }

    @Override
    public void run() {
        try {
            String commmand = dataInputStream.readUTF();
            System.out.println(commmand);
            if (commmand.equals("songChanged")) {
                String IP = dataInputStream.readUTF();
                String title = dataInputStream.readUTF();
                String artist = dataInputStream.readUTF();
            } else if (commmand.equals("getSong")) {
                File file = new File(MusicPlayer.currentSong.path);
                byte[] music = new byte[(int) file.length()];
                dataOutputStream.writeInt((int) file.length());
                outputStream.write(music, 0, music.length);
            } else if (commmand.equals("addFriends")) {
                while (true) {
                    System.out.println("vay");
                    try {
                        int size = dataInputStream.readInt();
                        if (size != 90) {
                            byte[] img = new byte[size];
                            inputStream.read(img);
                            String name = dataInputStream.readUTF();
                            String title = dataInputStream.readUTF();
                            String artist = dataInputStream.readUTF();
                            System.out.println(size + name + title + artist);
                            serverUpdate.addNewFriend(img, name, title, artist);
                        }
                        System.out.println(size);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public void setServerUpdate(ServerUpdate serverUpdate) {
        this.serverUpdate = serverUpdate;
    }
}
