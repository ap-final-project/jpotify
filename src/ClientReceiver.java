import java.io.*;


public class ClientReceiver implements Runnable {
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private ServerUpdate serverUpdate = null;
    private int count = 0;

    public ClientReceiver() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                String commmand = dataInputStream.readUTF();
                System.out.println(commmand);
                if (commmand.equals("songChanged")) {
                    System.out.println("kian khare");
                    String IP = dataInputStream.readUTF();
                    String title = dataInputStream.readUTF();
                    String artist = dataInputStream.readUTF();
                    serverUpdate.otherUsersSongChanged(IP, title, artist);
                } else if (commmand.equals("getSong")) {
                    int size = dataInputStream.readInt();
                    System.out.println("umade bede in sizesh : " + size);
                    byte[] music = new byte[size];
                    inputStream.read(music, 0, size);
                    File file=new File("weGotIt.mp3");
                    FileOutputStream fileOutputStream=new FileOutputStream(file);
                    fileOutputStream.write(music);
                } else if (commmand.equals("sendSong")) {

                } else if (commmand.equals("addFriends")) {
//                while (true) {
                    System.out.println("vay");
                    try {
                        int size = dataInputStream.readInt();
                        if (size != 90) {
                            count++;
                            System.out.println(count + " times");
                            byte[] img = new byte[size];
                            inputStream.read(img);
                            String name = dataInputStream.readUTF();
                            String title = dataInputStream.readUTF();
                            String artist = dataInputStream.readUTF();
                            System.out.println(size + name + title + artist);
                            System.out.println("name : " + name);
                            serverUpdate.addNewFriend(img, name, title, artist);
                        }
                        System.out.println(size);
                    } catch (IOException e) {
                        e.printStackTrace();
//                    }
                    }
                }

                commmand = "none";
            } catch (IOException e) {
                e.printStackTrace();
            }

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
