import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

public class LeftBar extends JPanel {
    AddPlaylistListener listener=null;
    JButton AddBtn=new JButton("Add");
    JLabel text=new JLabel();
    JButton home=new JButton("Home");

    public void setListener(AddPlaylistListener listener) {
        this.listener = listener;
    }

    JButton btn2=new JButton("browse");
    JButton btn3=new JButton("Library");
    JButton btn4=new JButton("Song");
    JButton btn5=new JButton("Albums");
        public LeftBar(){
            this.setBackground(Color.BLACK);
            AddBtn.setIcon(new ImageIcon("plus.png"));
            text.setText("Your menu kian merge");
            home.setBackground(Color.BLACK);
            home.setForeground(Color.WHITE);
            text.setForeground(Color.GREEN);
            AddBtn.setBackground(Color.BLACK);
            AddBtn.setForeground(Color.WHITE);
            AddBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    jfc.setDialogTitle("Multiple file and directory selection:");
                    jfc.setMultiSelectionEnabled(true);
                    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                    int returnValue = jfc.showDialog(null,"انتخاب موزیک");
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
                                listener.addToPlayList(x);
                            }
                        });
                    }
                }
            });
            this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            btn2.setBackground(Color.BLACK);
            btn3.setBackground(Color.BLACK);
            btn4.setBackground(Color.BLACK);
            btn5.setBackground(Color.BLACK);
            btn2.setForeground(Color.WHITE);
            btn3.setForeground(Color.WHITE);
            btn4.setForeground(Color.WHITE);
            btn5.setForeground(Color.WHITE);
            this.add(text);
            this.add(home);
            this.add(btn2);
            this.add(btn3);
            this.add(btn4);
            this.add(btn5);
            this.add(AddBtn,BorderLayout.PAGE_END);

        }
}

