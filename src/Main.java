import jaco.mp3.player.MP3Player;
import jaco.mp3.player.plaf.MP3PlayerUI;

import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) throws Exception {
        MainPage mainPage=new MainPage();
        mainPage.leftBar.setListener(mainPage.musicBar);
    }
}
