
//import org.farng.mp3.MP3File;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class Test {
    public Test(){
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Custom button");
//        MP3PlayerUI
        int returnValue = jfc.showDialog(null, "A button!");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println(jfc.getSelectedFile().getPath());
        }

    }
}
