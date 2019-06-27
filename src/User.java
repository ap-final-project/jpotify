import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class User implements InformSocket{
    private ClientSender clientSender;
    private ClientReceiver clientReceiver;
    private ArrayList<String> friendsIP=new ArrayList<>();
    private ArrayList<Friend> friends=new ArrayList<>();
    private String IP="2";
    private String name="pariya";
    private byte [] myImg;
    private String password;
    //    File
    Socket client;
    public User(String name,String password){
        try {
            clientReceiver=new ClientReceiver();
            clientSender=new ClientSender();
            this.name=name;
            this.password=password;
            readImage();
            client=new Socket("localhost",4051);
            clientSender.setOutputStream(client.getOutputStream());
            clientSender.setInputStream(client.getInputStream());
            clientSender.setDataOutputStream(new DataOutputStream(client.getOutputStream()));
            clientSender.setDataInputStream(new DataInputStream(client.getInputStream()));
            clientReceiver.setInputStream(client.getInputStream());
            clientReceiver.setOutputStream(client.getOutputStream());
            clientReceiver.setDataInputStream(new DataInputStream(client.getInputStream()));
            clientReceiver.setDataOutputStream(new DataOutputStream(client.getOutputStream()));
            System.out.println("connected");
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(client.getOutputStream());
            DataOutputStream dataOutputStream=new DataOutputStream(client.getOutputStream());
            objectOutputStream.writeObject(friendsIP);
            dataOutputStream.writeUTF(name);
            dataOutputStream.writeUTF(IP);
            dataOutputStream.writeInt(myImg.length);
            client.getOutputStream().write(myImg,0,myImg.length);
            client.getOutputStream().flush();
            Thread input=new Thread(clientReceiver);
            input.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientReceiver getClientReceiver() {
        return clientReceiver;
    }

    public void setClientReceiver(ClientReceiver clientReceiver) {
        this.clientReceiver = clientReceiver;
    }

    public ClientSender getClientSender() {
        return clientSender;
    }

    public void setClientSender(ClientSender clientSender) {
        this.clientSender = clientSender;
    }

    public ArrayList<String> getFriendsIPs() {
        return friendsIP;
    }

    public void setFriends(ArrayList<String> friendsIP) {
        this.friendsIP = friendsIP;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void setFriendsIP(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void getSong(String IP) throws IOException {
        System.out.println("umadam songe"+IP+"begiram");
        clientSender.getSong(IP);
    }

    @Override
    public void changeSong(String title, String artist) throws IOException {
        clientSender.songChanged(title,artist,IP);
        System.out.println("songam changid");
    }
    public  void readImage(){
        File myFile = new File ("img\\user.png");
        myImg  = new byte [(int)myFile.length()];
        try {
            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(myImg,0,myImg.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
