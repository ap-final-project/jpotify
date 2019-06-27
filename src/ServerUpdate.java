public interface ServerUpdate {//updates come from server
    void otherUsersSongChanged(String IP,String title,String artist);
    void addNewFriend(byte[] img,String name,String title,String artist);
}
