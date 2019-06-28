import javax.swing.*;
import java.io.*;

public class ClientReceiver implements Runnable {
//    private InputStream inputStream;
    private DataInputStream dataInputStream;
//    private OutputStream outputStream;
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
                else if (commmand.equals("getSong")) {
                    int size = dataInputStream.readInt();
                    System.out.println(size);
                    byte[] music = new byte[size];
//                    if(flag)
                    File file=new File("ahang"+size+".mp3");
//                    break;
                    int bytesRead = 0;
                    int totalBytes = 0;
                    FileOutputStream fileOutputStream=new FileOutputStream(file);
                    if(dataInputStream.readUTF().equals("salam")){
                        System.out.println("miram tu if line 43");
                        dataInputStream.readFully(music);
                            fileOutputStream.write(music);
                    }
                   System.out.println("song sent");
                }else if(commmand.equals("getPlaylist")){
                    ObjectInputStream objectInputStream=new ObjectInputStream(dataInputStream);
                    SendPlayList sendPlayList=(SendPlayList) objectInputStream.readObject();
                    String ip=dataInputStream.readUTF();
                    for (int i=0;i<sendPlayList.sharedSongs.size();i++  ) {
                        File file=new File("musicfrom"+ip+"-"+i+".mp3");
                        FileOutputStream fileOutputStream=new FileOutputStream(file);
                        fileOutputStream.write(sendPlayList.sharedSongs.get(i));
                    }
                }
                else if(commmand.equals("sendPlaylist")){
                    dataOutputStream.writeUTF("sendingSong");
                    SendPlayList sendPlayList=new SendPlayList();
                    sendPlayList.getPlayList(CenterPlayLists.playlistGUIs.get(2).playlist);
                    ObjectOutputStream objectOutputStream=new ObjectOutputStream(dataOutputStream);
                    objectOutputStream.writeObject(sendPlayList);
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
                    dataOutputStream.write(music);
                    dataOutputStream.flush();
//                    dataOutputStream.writeUTF("songSent");
                    dataOutputStream.flush();
                } else if (commmand.equals("addFriends")) {
//                while (true) {
                    System.out.println("vay");
                    try {
                        int size = dataInputStream.readInt();
                        if (size != 90) {
                            count++;
                            System.out.println(count + " times");
                            byte[] img = new byte[size];
                            dataInputStream.read(img);
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
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
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
