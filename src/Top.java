import javax.swing.*;
import java.awt.*;

public class Top extends Panel {
    JFormattedTextField textField;
    Label search;
    User user;
    public Top(User user) {
        super(3);
        this.user=user;
        textField = new JFormattedTextField();
        search = new Label("Search", 3);
        textField.setPreferredSize(new Dimension(85,20));
        textField.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.GRAY));
        Panel panel = new Panel(3);
        panel.setLayout(new FlowLayout());
        panel.add(search);
        panel.add(textField);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.WEST);
        UserGUI userGUI = new UserGUI(user);
        this.add(userGUI, BorderLayout.EAST);
    }
}