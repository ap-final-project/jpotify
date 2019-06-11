import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

public class LeftBar extends Panel {
    AddPlaylistListener listener = null;
    Button AddBtn = new Button("Add", 1);
    Label text = new Label(1);
    Button home = new Button("Home", 1);

    public void setListener(AddPlaylistListener listener) {
        this.listener = listener;
    }

    Button btn2 = new Button("browse", 1);
    Button btn3 = new Button("Library", 1);
    Button btn4 = new Button("Song", 1);
    Button btn5 = new Button("Albums", 1);

    public LeftBar() {
        super(1);
        Color bright = new Color(194, 194, 194);
        Color dark = new Color(24, 24, 24);
        AddBtn.setIcon(new ImageIcon("img\\plus.png"));
        text.setText("Menu");
        AddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Multiple file and directory selection:");
                jfc.setMultiSelectionEnabled(true);
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                int returnValue = jfc.showDialog(null, "انتخاب موزیک");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File[] files = jfc.getSelectedFiles();
                    System.out.println("Directories found\n");
                    Arrays.asList(files).forEach(x -> {
                        if (x.isDirectory()) {
                            System.out.println(x.getName());
                        }
                    });
                    System.out.println("\n- - - - - - - - - - -\n");
                    System.out.println("Files Found\n");
                    Arrays.asList(files).forEach(x -> {
                        if (x.isFile()) {
                            listener.addToPlayList(x.getPath());
                        }
                    });
                }
            }
        });
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        text.setBorder(new EmptyBorder(10,5,10,0));
        btn2.setBorder(new EmptyBorder(10,5,10,0));
        btn3.setBorder(new EmptyBorder(10,5,10,0));
        btn4.setBorder(new EmptyBorder(10,5,10,0));
        btn5.setBorder(new EmptyBorder(10,5,10,0));
        home.setBorder(new EmptyBorder(10,0,10,0));
        this.add(text);
        this.add(home);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
        this.add(AddBtn, BorderLayout.PAGE_END);
    }
}

