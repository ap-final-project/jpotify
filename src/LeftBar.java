import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.layout.Pane;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LeftBar extends Panel implements InformArtWrok {
    AddPlaylistListener listener = null;
    Button AddBtn = new Button("Add", 1);
    Button PLBtn = new Button("make new Playlist", 1);
    Label text = new Label(1);
    Button home = new Button("Home", 1);
    Button btn2 = new Button("PlayLists", 1);
    Button btn3 = new Button("Library", 1);
    Button btn4 = new Button("Song", 1);
    Button btn5 = new Button("Albums", 1);
    Panel artWork=new Panel(1);
    Panel leftBar=new Panel(1);
    Scroll scrollPane=new Scroll(leftBar);
    MakeVisibilityTrue centerTrue=null;
    MakeVisibilityTrue centerTrue1=null;

    public void setCenterTrue(MakeVisibilityTrue centerTrue) {
        this.centerTrue = centerTrue;
    }

    public void setCenterTrue1(MakeVisibilityTrue centerTrue1) {
        this.centerTrue1 = centerTrue1;
    }

    public LeftBar() {
        super(1);
        AddBtn.setIcon(new ImageIcon(new ImageIcon("img\\plus.png").getImage().getScaledInstance(28,28,100)));
        ArrayList<String> PlayLists=new ArrayList<>();
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
                    })
                    ;
                }
            }
        });

        PLBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String[] imgPath = {""};
                JFrame makePlayList=new JFrame();
                makePlayList.setVisible(true);
                makePlayList.setSize(150,150);
                makePlayList.setLocation(200,100);
                makePlayList.setLayout(new BorderLayout());
//                JTextPane textField=new JTextPane();
                TextField textField=new TextField();
//                textField.setName("Name");
                Button button=new Button("Add",1);
                Label label=new Label("Enter your playlist's name",1);
                Button addImg=new Button("select image",3);
                makePlayList.add(textField,BorderLayout.CENTER);
                makePlayList.add(label,BorderLayout.PAGE_START);
                Panel btnPanel=new Panel(3);
                btnPanel.setBorder(new EmptyBorder(2,2,2,2));
                btnPanel.setLayout(new GridLayout(1,2));
                btnPanel.add(addImg);
                btnPanel.add(button);
                makePlayList.add(btnPanel,BorderLayout.PAGE_END);
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                            if(!textField.getText().trim().equals("")){
                                System.out.println(textField.getText());
                                PlayLists.add(textField.getText());
                                listener.makePlayList(textField.getText(), imgPath[0]);
                                makePlayList.setVisible(false);
                            }
                        }
                    }
                });
                addImg.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                        int returnValue = jfc.showOpenDialog(null);
                        // int returnValue = jfc.showSaveDialog(null);

                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = jfc.getSelectedFile();
                            imgPath[0] = selectedFile.getAbsolutePath();
                        }
                    }
                });
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!textField.getText().trim().equals("")){
                            System.out.println(textField.getText());
                            PlayLists.add(textField.getText());
                            listener.makePlayList(textField.getText(), imgPath[0]);
                            makePlayList.setVisible(false);
                        }
                    }
                });
            }
        });


//        btn2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                centerTrue.makeTrue(1);
//            }
//        });
        btn2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerTrue.makeTrue(1);
            }
        });
//
//        btn4.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            }
//        });
        btn4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerTrue.makeTrue(0);
                System.out.println("hii");

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
        PLBtn.setBorder(new EmptyBorder(10,15,10,5));
        leftBar.add(text);
        leftBar.add(home);
        leftBar.add(btn2);
        leftBar.add(btn3);
        leftBar.add(btn4);
        leftBar.add(btn5);
        leftBar.add(AddBtn);
        leftBar.add(PLBtn);
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
        revalidate();
        repaint();
    }
}

