import javax.swing.*;
import java.io.*;


public class ClientReceiver implements Runnable {
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private ServerUpdate serverUpdate = null;
    private int count = 0;
    private boolean flag=true;
    public ClientReceiver() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                String commmand = dataInputStream.readUTF();
                System.out.println("command : "+    commmand);
                if (commmand.equals("songChanged")) {
                    System.out.println("kian khare");
                    String IP = dataInputStream.readUTF();
                    String title = dataInputStream.readUTF();
                    String artist = dataInputStream.readUTF();
                    serverUpdate.otherUsersSongChanged(IP, title, artist);
                }
                else if(commmand.equals("tolokhoda")){

                }
                else if (commmand.equals("getSong")) {
                    System.out.println("inja miayyyy?????");
                    int size = dataInputStream.readInt();
                    System.out.println(size);
                    System.out.println("umade bede in sizesh : " + size);
                    byte[] music = new byte[size];
//                    if(flag)
                        File file=new File("weGotIt.mp3");
//                    break;
                    FileOutputStream fileOutputStream=new FileOutputStream(file);
                    inputStream.read(music);
                    int count=0;
                    while ((count=inputStream.read(music))>0) {
                        fileOutputStream.write(music,0,count);
//                }
                    } System.out.println("biroooooooooooooooooooooooooooooon");
                }




                else if (commmand.equals("sendSong")) {
                    File file=new File(MusicPlayer.currentSong.path);
                    FileInputStream fis=new FileInputStream(file);
                    dataOutputStream.writeUTF("sendingSong");
                    byte[] music=new byte[(int)file.length()];
                    System.out.println("file siiiiiiiiiiize : "+file.length());
                    fis.read(music);
                    dataOutputStream.writeInt(music.length);
                        dataOutputStream.flush();
                    outputStream.write(music);
                    outputStream.flush();
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
