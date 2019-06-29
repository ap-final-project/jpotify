import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Top extends Panel {
    JFormattedTextField textField;
    Label search;
    User user;
    Searching searching;
    MakeVisibilityTrue makeVisibilityTrue;
    public Top(User user) {
        super(3);
        this.user=user;
        textField = new JFormattedTextField();
        search = new Label( 3);
        search.setIcon(new ImageIcon("img\\search.png"));
        textField.setPreferredSize(new Dimension(105,20));
        textField.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(40,40,40)));
        Panel panel = new Panel(3);
        panel.setLayout(new FlowLayout());
        panel.add(textField);
        panel.add(search);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.WEST);
        UserGUI userGUI = new UserGUI(user);
        this.add(userGUI, BorderLayout.EAST);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searching.search(textField.getText());
                System.out.println("searching");
                makeVisibilityTrue.makeTrue(7);
            }
        });
    }

    public void setSearching(Searching searching) {
        this.searching = searching;
    }

    public void setMakeVisibilityTrue(MakeVisibilityTrue makeVisibilityTrue) {
        this.makeVisibilityTrue = makeVisibilityTrue;
    }
}