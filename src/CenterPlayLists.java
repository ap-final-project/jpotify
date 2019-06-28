import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CenterPlayLists extends Panel implements AddPlaylistListener,AddSharedPlaylist {
    static ArrayList<PLGUI> playlistGUIs = new ArrayList<>();
    MakeVisibilityTrue makeVisibilityTrue = null;
    ChoosePlaylist choosePlaylistListener = null;
    ArrayList<Playlist> playlists;
    AddSong addSong;
    public void setChoosePlaylistListener(ChoosePlaylist choosePlaylistListener) {
        this.choosePlaylistListener = choosePlaylistListener;
    }

    public void setMakeVisibilityTrue(MakeVisibilityTrue makeVisibilityTrue) {
        this.makeVisibilityTrue = makeVisibilityTrue;
    }

    public CenterPlayLists(ArrayList<Playlist> playlists) {
        super(2);
        this.playlists = playlists;
        System.out.println(playlists.size());
        for (Playlist pl : playlists) {
            PLGUI plgui = new PLGUI(pl, pl.name, pl.imgPath);
            playlistGUIs.add(plgui);
            if(pl.equals(playlists.get(0))||pl.equals(playlists.get(1)) || pl.equals(playlists.get(2)) ) {
                plgui.remove(2);}
                else{
                plgui.more.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        JPopupMenu jPopupMenu = new JPopupMenu();
                        JMenuItem edit = new JMenuItem("Edit");
                        JMenuItem remove = new JMenuItem("Remove");
                        JMenuItem add = new JMenuItem("Add");
                        jPopupMenu.add(edit);
                        jPopupMenu.add(remove);
                        jPopupMenu.add(add);
                        jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                        remove.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                playlists.remove(plgui.playlist);
                                playlistGUIs.remove(plgui);
                                CenterPlayLists.this.remove(plgui);
                                revalidate();
                            }
                        });
                        edit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                final String[] imgPath = {""};
                                JFrame makePlayList = new JFrame();
                                makePlayList.setVisible(true);
                                makePlayList.setSize(150, 150);
                                makePlayList.setLocation(200, 100);
                                makePlayList.setLayout(new BorderLayout());
                                TextField textField = new TextField();
                                TextArea textArea = new TextArea();
                                Button addNewPL = new Button("Add", 1);
                                Label label = new Label("Enter your playlist's new name", 1);
                                Button addImg = new Button("select image", 3);
                                Panel p = new Panel(3);
                                p.setLayout(new GridLayout(2, 1));
                                p.add(label);
                                p.add(textField);
                                makePlayList.add(p, BorderLayout.PAGE_START);
                                Panel btnPanel = new Panel(3);
                                Label description = new Label("Description", 3);
                                Panel p1 = new Panel(3);
                                p1.setLayout(new BorderLayout());
                                p1.add(description, BorderLayout.PAGE_START);
                                p1.add(textArea, BorderLayout.CENTER);
                                btnPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
                                btnPanel.setLayout(new GridLayout(1, 2));
                                btnPanel.add(addImg);
                                makePlayList.add(p1, BorderLayout.CENTER);
                                btnPanel.add(addNewPL);
                                makePlayList.add(btnPanel, BorderLayout.PAGE_END);
                                textField.addKeyListener(new KeyAdapter() {
                                    @Override
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
                                        if (!textField.getText().trim().equals("")) {
                                            if (imgPath[0].equals("")) {
                                                imgPath[0] = plgui.playlist.imgPath;
                                            }
                                            plgui.playlist.name = textField.getText();
                                            plgui.playlist.description = textArea.getText();
                                            plgui.playlist.imgPath = imgPath[0];
                                            plgui.label.setText("<html>" + " " + plgui.playlist.name + "<br>" + " " + plgui.playlist.description + "<br>" + " " + plgui.playlist.songs.size() + "songs" + "</html>");
                                            Image image = Toolkit.getDefaultToolkit().getImage(imgPath[0]);
                                            Image newimg = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                                            ImageIcon imageIcon = new ImageIcon(newimg);
                                            plgui.pic.setIcon(imageIcon);
                                            makePlayList.setVisible(false);
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
            plgui.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    choosePlaylistListener.setPlaylist(plgui.getPlaylist());
                    makeVisibilityTrue.makeTrue(0);
                }
            });
            this.add(plgui);
            revalidate();
        }
        this.setLayout(new WrapLayout(WrapLayout.LEFT));
//        PLGUI favGUI=new PLGUI(playlists.get(1),playlists.get(1).name,playlists.get(1).imgPath);
//        favGUI.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                choosePlaylistListener.setPlaylist(playlists.get(1));
//                makeVisibilityTrue.makeTrue(0);
//            }
//        });
//        this.add(favGUI);
        revalidate();
    }

    @Override
    public void makePlayList(String name, String description, String path) {
        Playlist playlist = new Playlist(name, description, path);
        PLGUI plgui = new PLGUI(playlist, name, path);
        playlists.add(playlist);
        playlistGUIs.add(plgui);
        plgui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choosePlaylistListener.setPlaylist(plgui.getPlaylist());
                makeVisibilityTrue.makeTrue(0);
            }
        });

        plgui.more.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JPopupMenu jPopupMenu = new JPopupMenu();
                JMenuItem edit = new JMenuItem("Edit");
                JMenuItem remove = new JMenuItem("Remove");
                JMenuItem add = new JMenuItem("Add");
                jPopupMenu.add(edit);
                jPopupMenu.add(remove);
                jPopupMenu.add(add);
                jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                remove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    playlists.remove(playlist);
                    playlistGUIs.remove(plgui);
                    CenterPlayLists.this.remove(plgui);
                    revalidate();
                    }
                });
                edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final String[] imgPath = {""};
                        JFrame makePlayList = new JFrame();
                        makePlayList.setVisible(true);
                        makePlayList.setSize(150, 150);
                        makePlayList.setLocation(200, 100);
                        makePlayList.setLayout(new BorderLayout());
                        TextField textField = new TextField();
                        TextArea textArea = new TextArea();
                        Button addNewPL = new Button("Change", 1);
                        Label label = new Label("Enter your playlist's new name", 1);
                        Button addImg = new Button("select image", 3);
                        Panel p = new Panel(3);
                        p.setLayout(new GridLayout(2, 1));
                        p.add(label);
                        p.add(textField);
                        makePlayList.add(p, BorderLayout.PAGE_START);
                        Panel btnPanel = new Panel(3);
                        Label description = new Label("Description", 3);
                        Panel p1 = new Panel(3);
                        p1.setLayout(new BorderLayout());
                        p1.add(description, BorderLayout.PAGE_START);
                        p1.add(textArea, BorderLayout.CENTER);
                        btnPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
                        btnPanel.setLayout(new GridLayout(1, 2));
                        btnPanel.add(addImg);
                        makePlayList.add(p1, BorderLayout.CENTER);
                        btnPanel.add(addNewPL);
                        makePlayList.add(btnPanel, BorderLayout.PAGE_END);
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
                                if (!textField.getText().trim().equals("")) {
                                    if (imgPath[0].equals("")) {
                                        imgPath[0] = plgui.playlist.imgPath;
                                    }
                                    plgui.playlist.name = textField.getText();
                                    plgui.playlist.description = textArea.getText();
                                    plgui.playlist.imgPath = imgPath[0];
                                    plgui.label.setText("<html>" + " " + playlist.name + "<br>" + " " + playlist.description + "<br>" + " " + playlist.songs.size() + "songs" + "</html>");
                                    Image image=Toolkit.getDefaultToolkit().getImage(imgPath[0]);
                                    Image newimg = image.getScaledInstance(200, 200,  Image.SCALE_SMOOTH);
                                    ImageIcon imageIcon=new ImageIcon(newimg);
                                    plgui.pic.setIcon(imageIcon);
                                    makePlayList.setVisible(false);
                                }
                            }
                        });
                    }
                });
            }
        });
        this.add(plgui);
        revalidate();
    }

    @Override
    public void addSharedPlaylist(ArrayList<String> paths,String IP) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException {
        Playlist playlist=new Playlist(IP+"'s shared playlist","listen what others are listening","img\\sharedPlaylist.jpeg");
        PLGUI plgui = new PLGUI(playlist, IP+"'s shared playlist", "img\\sharedPlaylist.jpeg");
        playlists.add(playlist);
        playlistGUIs.add(plgui);
        plgui.remove(2);
        plgui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choosePlaylistListener.setPlaylist(plgui.getPlaylist());
                makeVisibilityTrue.makeTrue(0);
            }
        });
        for (String path:paths) {
            Song song=new Song(path);
            SongGUI songGUI=new SongGUI(song);
            addSong.addSong(song,songGUI);
            playlist.add(songGUI,song);
        }
        this.add(plgui);
    }

    public void setAddSong(AddSong addSong) {
        this.addSong = addSong;
    }
}
