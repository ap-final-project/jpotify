import java.io.*;


public class ClientReceiver implements Runnable {
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    public ClientReceiver(){
    }
    @Override
    public void run() {
        try {
            String commmand=dataInputStream.readUTF();
            if (commmand.equals("songChanged")){
                String IP=dataInputStream.readUTF();
                String title=dataInputStream.readUTF();
                String artist=dataInputStream.readUTF();

            }else if (commmand.equals("getSong")){
                File file=new File(MusicPlayer.currentSong.path);
                byte[] music=new byte[(int) file.length()];
                dataOutputStream.writeInt((int) file.length());
                outputStream.write(music,0,music.length);
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
}
