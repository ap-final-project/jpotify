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
import java.util.Random;

/**
 * Menu panel at the left of the screen
 */
public class LeftBar extends Panel implements InformArtWrok{
    private AddPlaylistListener makePlListener = null;
    private AddSong addSongListener=null;
    private MakeAlbumListener makeAlbum=null;
    private Button AddBtn = new Button("Add Songs", 1);
    private Button PLBtn = new Button("make new Playlist", 1);
    private Label text = new Label(1);
    private Button AddBtnMovie = new Button("Add Movies", 1);
    private Button playlistsBTN = new Button("PlayLists", 1);
    private Button movie = new Button("Movie", 1);
    private Button songBtn = new Button("Song", 1);
    private Button albumBTN = new Button("Albums", 1);
    private Button movieLibrary=new Button("Movie Library",1);
    private Panel artWork=new Panel(1);
    private Panel leftBar=new Panel(1);
    private Scroll scrollPane=new Scroll(leftBar);
    private MakeVisibilityTrue centerTrue=null;
    private ChoosePlaylist choosePlaylist;
    private ArrayList<Song> songs=new ArrayList<>();
    private ArrayList<SongGUI> songGUIS=new ArrayList<>();
    private String[] playlistDefaultIMGS=new String[3];
    private AddMovie addMovie;


    public LeftBar() {
        super(1);
        AddBtn.setIcon(new ImageIcon(new ImageIcon("img\\plus.png").getImage().getScaledInstance(28,28,100)));
        AddBtnMovie.setIcon(new ImageIcon(new ImageIcon("img\\plus.png").getImage().getScaledInstance(28,28,100)));
        PLBtn.setIcon(new ImageIcon(new ImageIcon("img\\plus.png").getImage().getScaledInstance(28,28,100)));
        text.setText("Library");
        playlistDefaultIMGS[0]="img\\PLDefault (1).jpg";
        playlistDefaultIMGS[1]="img\\PLDefault (2).jpg";
        playlistDefaultIMGS[2]="img\\PLDefault (3).jpg";
        AddBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Multiple file and directory selection:");
                jfc.setMultiSelectionEnabled(true);
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int returnValue = jfc.showDialog(null, "choose");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File[] files = jfc.getSelectedFiles();
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
            }
        });

        PLBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String[] imgPath = {""};
                JFrame makePlayList=new JFrame();
                makePlayList.setVisible(true);
                makePlayList.setSize(400,300);
                makePlayList.setLocation(800,500);
                makePlayList.setLayout(new BorderLayout());
                TextField textField=new TextField();
                TextArea textArea=new TextArea();
                textField.setForeground(new Color(18,18,18));
                textArea.setForeground(new Color(18,18,18));
                Button addNewPL=new Button("Add",3);
                Label label= new Label("Enter your playlist's name",3);
                Button addImg=new Button(3);
                addImg.setIcon(new ImageIcon("img\\addImg.png"));
                Panel p=new Panel(3);
                p.setLayout(new GridLayout(2,1));
                p.add(label);
                p.add(textField);
                addImg.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
                makePlayList.add(p,BorderLayout.PAGE_START);
                Panel btnPanel=new Panel(3);
                Label description=new Label("Description",3);
                label.setFont(new Font("Cambria", Font.BOLD, 17));
                description.setFont(new Font("Cambria", Font.BOLD, 17));
                addNewPL.setFont(new Font("Cambria", Font.BOLD, 17));
                Panel p1=new Panel(3);
                p1.setLayout(new BorderLayout());
                p1.add(description,BorderLayout.PAGE_START);
                p1.add(addImg,BorderLayout.EAST);
                p1.add(textArea,BorderLayout.CENTER);
                btnPanel.setBorder(new EmptyBorder(2,2,2,2));
                makePlayList.add(p1,BorderLayout.CENTER);
                btnPanel.add(addNewPL);
                makePlayList.add(btnPanel,BorderLayout.PAGE_END);
                textField.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                        if(e.getKeyCode()==KeyEvent.VK_ENTER)
                            addNewPL.doClick();
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
                            Random random=new Random();
                            if (imgPath[0].equals("")) {
                                imgPath[0] = playlistDefaultIMGS[Math.abs(random.nextInt()%3)];
                            }
                            makePlListener.makePlayList(textField.getText(),textArea.getText(), imgPath[0]);
                            makePlayList.setVisible(false);
                        }
                    }
                });
            }
        });
        AddBtnMovie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String path;
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    path = selectedFile.getAbsolutePath();
                    addMovie.addMovie(path);
                    centerTrue.makeTrue(5);
                }


            }
        });
        movie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               centerTrue.makeTrue(6);
            }
        });
        playlistsBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerTrue.makeTrue(1);
            }
        });
        movieLibrary.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerTrue.makeTrue(5);

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
        playlistsBTN.setBorder(new EmptyBorder(10,15,10,0));
        movie.setBorder(new EmptyBorder(10,15,10,0));
        songBtn.setBorder(new EmptyBorder(10,15,10,0));
        albumBTN.setBorder(new EmptyBorder(10,15,10,0));
        AddBtnMovie.setBorder(new EmptyBorder(10,15,10,0));
        AddBtn.setBorder(new EmptyBorder(10,15,10,5));
        PLBtn.setBorder(new EmptyBorder(10,15,10,5));
        movieLibrary.setBorder(new EmptyBorder(10,15,10,5));
        leftBar.add(text);
        leftBar.add(albumBTN);
        leftBar.add(songBtn);
        leftBar.add(playlistsBTN);
        leftBar.add(movie);
        leftBar.add(movieLibrary);
        leftBar.add(PLBtn);
        leftBar.add(AddBtn);
        leftBar.add(AddBtnMovie);
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

    public void setAddMovie(AddMovie addMovie) {
        this.addMovie=addMovie;
    }
    public void setCenterTrue(MakeVisibilityTrue centerTrue) {
        this.centerTrue = centerTrue;
    }

    public void setaddSongListener(AddSong addSongListener) {
        this.addSongListener = addSongListener;
    }

    public void setMakeAlbum(MakeAlbumListener makeAlbum) {
        this.makeAlbum = makeAlbum;
    }
}

