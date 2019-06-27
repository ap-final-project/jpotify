public class Friend {
    private String name="";
    private String title="";
    private String artist="";
    private String IP="";
    private byte[] img;
    private Playlist sharedPL;
    private boolean isOnline;
    private String lastSeen;
    public Friend(String IP){
        this.IP=IP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Playlist getSharedPL() {
        return sharedPL;
    }

    public void setSharedPL(Playlist sharedPL) {
        this.sharedPL = sharedPL;
    }
}
