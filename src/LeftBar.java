import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class LeftBar extends Panel implements InformArtWrok {
    AddPlaylistListener listener = null;
    Button AddBtn = new Button("Add", 1);
    Label text = new Label(1);
    Button home = new Button("Home", 1);
    Button btn2 = new Button("browse", 1);
    Button btn3 = new Button("Library", 1);
    Button btn4 = new Button("Song", 1);
    Button btn5 = new Button("Albums", 1);
    Panel artWork=new Panel(1);
    Panel leftBar=new Panel(1);
    Scroll scrollPane=new Scroll(leftBar);
    public LeftBar() {
        super(1);
        Color bright = new Color(194, 194, 194);
        Color dark = new Color(24, 24, 24);
        AddBtn.setIcon(new ImageIcon(new ImageIcon("img\\plus.png").getImage().getScaledInstance(28,28,100)));
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
                            try {
                                listener.addToPlayList(x.getPath());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedTagException e1) {
                                e1.printStackTrace();
                            } catch (InvalidDataException e1) {
                                e1.printStackTrace();
                            } catch (JavaLayerException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
//        GridBagConstraints c = new GridBagConstraints();
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 0;
//        c.insets = new Insets(0,0,0,0);
//        c.weighty = 1.0;
        this.add(scrollPane);
        this.add(artWork);
        leftBar.setLayout(new BoxLayout(leftBar,BoxLayout.Y_AXIS));
        text.setBorder(new EmptyBorder(20,15,10,0));
        btn2.setBorder(new EmptyBorder(10,15,10,0));
        btn3.setBorder(new EmptyBorder(10,15,10,0));
        btn4.setBorder(new EmptyBorder(10,15,10,0));
        btn5.setBorder(new EmptyBorder(10,15,10,0));
        home.setBorder(new EmptyBorder(10,15,10,0));
        AddBtn.setBorder(new EmptyBorder(10,15,10,5));
        leftBar.add(text);
        leftBar.add(home);
        leftBar.add(btn2);
        leftBar.add(btn3);
        leftBar.add(btn4);
        leftBar.add(btn5);
        leftBar.add(AddBtn);
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.WHITE));
        leftBar.setBorder(new EmptyBorder(0,0,2,0));
        artWork.setLayout(new BorderLayout());
        artWork.setPreferredSize(new Dimension(200,200));
        artWork.setMaximumSize(new Dimension(200,200));
        artWork.setMaximumSize(new Dimension(200,200));
    }
    public void setListener(AddPlaylistListener listener) {
        this.listener = listener;
    }
    @Override
    public void setArtwork(Label label) {
        if (artWork.getComponents().length==1) artWork.remove(0);
        artWork.add(label,BorderLayout.PAGE_END);
    }
}

