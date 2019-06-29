import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import java.io.*;
import java.util.ArrayList;

/**
 * For receiving data from server.
 */
public class ClientReceiver implements Runnable {
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ServerUpdate serverUpdate = null;
    private int count = 0;
    private AddSharedPlaylist addSharedPlaylist;

    public ClientReceiver() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                String commmand = dataInputStream.readUTF();
                if (commmand.equals("songChanged")) {
                    String IP = dataInputStream.readUTF();
                    String title = dataInputStream.readUTF();
                    String artist = dataInputStream.readUTF();
                    serverUpdate.otherUsersSongChanged(IP, title, artist);
                } else if (commmand.equals("getSong")) {
                    int size = dataInputStream.readInt();
                    byte[] music = new byte[size];
                    File file = new File("ahang" + size + ".mp3");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    String ip = dataInputStream.readUTF();
                    dataInputStream.readFully(music);
                    fileOutputStream.write(music);
                    ArrayList<String> paths = new ArrayList<>();
                    paths.add("ahang" + size + ".mp3");
                    addSharedPlaylist.addSharedPlaylist(paths, ip);
                } else if (commmand.equals("getPlaylist")) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(dataInputStream);
                    SendPlayList sendPlayList = new SendPlayList();
                    sendPlayList.sharedSongs = (ArrayList<byte[]>) objectInputStream.readObject();
                    String ip = dataInputStream.readUTF();
                    ArrayList<String> paths = new ArrayList<>();
                    for (int i = 0; i < sendPlayList.sharedSongs.size(); i++) {
                        File file = new File("musicfrom" + ip + "-" + i + ".mp3");
                        paths.add("musicfrom" + ip + "-" + i + ".mp3");
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(sendPlayList.sharedSongs.get(i));
                    }
                    addSharedPlaylist.addSharedPlaylist(paths, ip);
                } else if (commmand.equals("sendPlaylist")) {
                    dataOutputStream.writeUTF("sendingSong");
                    SendPlayList sendPlayList = new SendPlayList();
                    sendPlayList.getPlayList(CenterPlayLists.playlistGUIs.get(2).playlist);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(dataOutputStream);
                    objectOutputStream.writeObject(sendPlayList.sharedSongs);
                } else if (commmand.equals("sendSong")) {
                    File file = new File(MusicPlayer.currentSong.path);
                    FileInputStream fis = new FileInputStream(file);
                    dataOutputStream.writeUTF("sendingSong");
                    byte[] music = new byte[(int) file.length()];
                    fis.read(music);
                    dataOutputStream.writeInt(music.length);
                    dataOutputStream.flush();
                    dataOutputStream.write(music);
                    dataOutputStream.flush();
                    dataOutputStream.flush();
                } else if (commmand.equals("addFriends")) {
                    try {
                        int size = dataInputStream.readInt();
                        if (size != 90) {
                            byte[] img = new byte[size];
                            dataInputStream.read(img);
                            String name = dataInputStream.readUTF();
                            String title = dataInputStream.readUTF();
                            String artist = dataInputStream.readUTF();
                            serverUpdate.addNewFriend(img, name, title, artist);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     *
     * @param dataInputStream
     */
    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    /**
     *
     * @param dataOutputStream
     */
    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public void setServerUpdate(ServerUpdate serverUpdate) {
        this.serverUpdate = serverUpdate;
    }

    public void setAddSharedPlaylist(AddSharedPlaylist addSharedPlaylist) {
        this.addSharedPlaylist = addSharedPlaylist;
    }
}
