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

public class LeftBar extends Panel implements InformArtWrok{
    AddPlaylistListener makePlListener = null;
    AddSong addSongListener=null;
    MakeAlbumListener makeAlbum=null;
    Button AddBtn = new Button("Add", 1);
    Button PLBtn = new Button("make new Playlist", 1);
    Label text = new Label(1);
    Button home = new Button("Home", 1);
    Button btn2 = new Button("PlayLists", 1);
    Button btn3 = new Button("Library", 1);
    Button songBtn = new Button("Song", 1);
    Button albumBTN = new Button("Albums", 1);
    Panel artWork=new Panel(1);
    Panel leftBar=new Panel(1);
    Scroll scrollPane=new Scroll(leftBar);
    MakeVisibilityTrue centerTrue=null;
    ChoosePlaylist choosePlaylist;
    ArrayList<Song> songs=new ArrayList<>();
    ArrayList<SongGUI> songGUIS=new ArrayList<>();
    public void setCenterTrue(MakeVisibilityTrue centerTrue) {
        this.centerTrue = centerTrue;
    }

    public void setaddSongListener(AddSong addSongListener) {
        this.addSongListener = addSongListener;
    }

    public void setMakeAlbum(MakeAlbumListener makeAlbum) {
        this.makeAlbum = makeAlbum;
    }

    public LeftBar() {
        super(1);
        AddBtn.setIcon(new ImageIcon(new ImageIcon("img\\plus.png").getImage().getScaledInstance(28,28,100)));
        text.setText("Menu");
        AddBtn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Multiple file and directory selection:");
            jfc.setMultiSelectionEnabled(true);
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int returnValue = jfc.showDialog(null, "choose");
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File[] files = jfc.getSelectedFiles();
                System.out.println("Directories found\n");
                Arrays.asList(files).forEach(x -> {
                    if (x.isDirectory()) {
                        ArrayList<String> path=new ArrayList<>();
                        for (int i = 0; i <x.listFiles().length ; i++) {
                            Song song= null;
                            SongGUI songGUI= null;
                            try {
                                song = new Song(x.listFiles()[i].getPath());
                                songGUI=new SongGUI(song);
                                songs.add(song);
                                songGUIS.add(songGUI);
                            } catch (JavaLayerException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (InvalidDataException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedTagException e1) {
                                e1.printStackTrace();
                            }
                            addSongListener.addSong(song,songGUI);
                            path.add(x.listFiles()[i].getPath());
                        }
                        makeAlbum.makeAlbum(x.getName(),songs,songGUIS);
                    }
                });
                System.out.println("\n- - - - - - - - - - -\n");
                System.out.println("Files Found\n");
                Arrays.asList(files).forEach(x -> {
                    if (x.isFile()) {
                        Song song= null;
                        SongGUI songGUI=null;
                        try {
                            song = new Song(x.getPath());
                            songGUI=new SongGUI(song);
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                        addSongListener.addSong(song,songGUI);
                        makeAlbum.makeAlbumS(song.album,song,songGUI);
                    }
                })
                ;
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
                TextArea textArea=new TextArea();
//                textField.setName("Name");
                Button addNewPL=new Button("Add",1);
                Label label= new Label("Enter your playlist's name",1);
                Button addImg=new Button("select image",3);
                Panel p=new Panel(3);
                p.setLayout(new GridLayout(2,1));
                p.add(label);
                p.add(textField);
                makePlayList.add(p,BorderLayout.PAGE_START);
                Panel btnPanel=new Panel(3);
                Label description=new Label("Description",3);
                Panel p1=new Panel(3);
                p1.setLayout(new BorderLayout());
                p1.add(description,BorderLayout.PAGE_START);
                p1.add(textArea,BorderLayout.CENTER);
                btnPanel.setBorder(new EmptyBorder(2,2,2,2));
                btnPanel.setLayout(new GridLayout(1,2));
                btnPanel.add(addImg);
                makePlayList.add(p1,BorderLayout.CENTER);
                btnPanel.add(addNewPL);
                makePlayList.add(btnPanel,BorderLayout.PAGE_END);
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                            if(!textField.getText().trim().equals("")){
                                makePlListener.makePlayList(textField.getText(),textArea.getText(), imgPath[0]);
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
                addNewPL.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!textField.getText().trim().equals("")){
                            makePlListener.makePlayList(textField.getText(),textArea.getText(), imgPath[0]);
                            makePlayList.setVisible(false);
                        }
                    }
                });
            }
        });
        btn2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerTrue.makeTrue(1);
            }
        });
        songBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choosePlaylist.setPlaylist(null);
                centerTrue.makeTrue(0);
            }
        });
        albumBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerTrue.makeTrue(2);
            }
        });

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(scrollPane);
        this.add(artWork);
        leftBar.setLayout(new BoxLayout(leftBar,BoxLayout.Y_AXIS));
        text.setBorder(new EmptyBorder(20,15,10,0));
        btn2.setBorder(new EmptyBorder(10,15,10,0));
        btn3.setBorder(new EmptyBorder(10,15,10,0));
        songBtn.setBorder(new EmptyBorder(10,15,10,0));
        albumBTN.setBorder(new EmptyBorder(10,15,10,0));
        home.setBorder(new EmptyBorder(10,15,10,0));
        AddBtn.setBorder(new EmptyBorder(10,15,10,5));
        PLBtn.setBorder(new EmptyBorder(10,15,10,5));
        leftBar.add(text);
        leftBar.add(home);
        leftBar.add(btn2);
        leftBar.add(btn3);
        leftBar.add(songBtn);
        leftBar.add(albumBTN);
        leftBar.add(AddBtn);
        leftBar.add(PLBtn);
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.WHITE));
        leftBar.setBorder(new EmptyBorder(0,0,2,0));
        artWork.setLayout(new BorderLayout());
        artWork.setPreferredSize(new Dimension(200,200));
        artWork.setMaximumSize(new Dimension(200,200));
        artWork.setMaximumSize(new Dimension(200,200));
    }
    public void setmakePlListener(AddPlaylistListener makePlListener) {
        this.makePlListener = makePlListener;
    }
    @Override
    public void setArtwork(Label label) {
        if (artWork.getComponents().length==1) artWork.remove(0);
        artWork.add(label,BorderLayout.PAGE_END);
        revalidate();
        repaint();
    }

    public void setChoosePlaylist(ChoosePlaylist choosePlaylist) {
        this.choosePlaylist = choosePlaylist;
    }
}

