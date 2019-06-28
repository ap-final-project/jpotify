import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class User implements InformSocket,Serializable{
    transient private ClientSender clientSender;
    transient private ClientReceiver clientReceiver;
    transient private Socket client;
    private  ArrayList<String> friendsIP=new ArrayList<>();
    private  ArrayList<Friend> friends=new ArrayList<>();
    private  String IP;
    private  String name;
    private  byte [] myImg;
    private String imgPath;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    private  String password;

    //    File
    public User(String name,String password,String imgPath){
        try {
            this.name=name;
            this.IP=name;
            this.password=password;
            this.imgPath=imgPath;
            readImage();
            clientReceiver=new ClientReceiver();
            clientSender=new ClientSender();
            client=new Socket("localhost",1234);
//            clientSender.setOutputStream(client.getOutputStream());
//            clientSender.setInputStream(client.getInputStream());
            clientSender.setDataOutputStream(new DataOutputStream(client.getOutputStream()));
            clientSender.setDataInputStream(new DataInputStream(client.getInputStream()));
//            clientReceiver.setInputStream(client.getInputStream());
//            clientReceiver.setOutputStream(client.getOutputStream());
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

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    public void setImg(byte[] myImg) {
        this.myImg = myImg;
    }

    public byte[] getImg() {
        return myImg;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void setFriendsIP(ArrayList<String> friendsIP) {
        this.friendsIP = friendsIP;
    }


    public String getPassword() {
        return password;
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

    @Override
    public void getPlaylist(String IP) {
        try {
            clientSender.getPL(IP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void readImage(){
        System.out.println("injaaaa njonvievnoeiwv");
        File myFile = new File (imgPath);
        System.out.println(imgPath);
        myImg  = new byte [(int)myFile.length()];
        try {
            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(myImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
