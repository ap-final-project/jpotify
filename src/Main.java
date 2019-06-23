import java.awt.*;
import java.io.File;
import java.net.URL;

import javax.swing.*;

public class Main{
    public static void main(String[] args) throws Exception {
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//        for ( int i = 0; i < fonts.length; i++ )
//        {
//            System.out.println(fonts[i]);
//        }
//        WelcomeJFrame welcome=new WelcomeJFrame();
//        welcome.setVisible(true);
//        Thread.sleep(4000);
//        welcome.setVisible(false);
        MainPage mainPage=new MainPage();
    }
}

