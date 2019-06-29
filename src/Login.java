import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login frame that opens before program starts running
 */
public class Login extends JFrame {
    Label userLabel, passLabel;
    JTextField userText, passText;
    String userName;
    String passWord;
    Label welcome;
    Button login;
    Button photo;
    Button signUp;
    Login() {
        setTitle("LOGIN FORM");
        welcome=new Label("<html>"+"Welcome to JPotify!!!"+"<br>"+"Plaease enter your username and password:)"+"</html>",3);
        login=new Button("Login",3);
        photo=new Button("add img",3);
        signUp=new Button("signUp",3);
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);
        userLabel = new Label("username : ",3);
        passLabel = new Label("password : ",3);
        userText = new JTextField();
        passText = new JPasswordField();
        userLabel.setPreferredSize(new Dimension(120,40));
        passLabel.setPreferredSize(new Dimension(120,40));
        passText.setPreferredSize(new Dimension(120,40));
        userText.setPreferredSize(new Dimension(120,40));
        login.setPreferredSize(new Dimension(80,20));
        signUp.setPreferredSize(new Dimension(80,20));
        userName = userText.getText();
        this.setSize(400,400);
        passWord = passText.getText();
        springLayout.putConstraint(SpringLayout.NORTH,welcome,10,SpringLayout.NORTH,this);
        springLayout.putConstraint(SpringLayout.WEST,welcome,40,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,userLabel,50,SpringLayout.NORTH,welcome);
        springLayout.putConstraint(SpringLayout.NORTH,userText,50,SpringLayout.NORTH,welcome);
        springLayout.putConstraint(SpringLayout.SOUTH, passLabel, 110, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, passText, 110, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.WEST, userText, 30, SpringLayout.EAST, userLabel);
        springLayout.putConstraint(SpringLayout.WEST, passText, 30, SpringLayout.EAST, passLabel);
        springLayout.putConstraint(SpringLayout.NORTH, signUp, 60, SpringLayout.SOUTH, passLabel);
        springLayout.putConstraint(SpringLayout.NORTH, login, 60, SpringLayout.SOUTH, passText);
        springLayout.putConstraint(SpringLayout.WEST,login,70,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.WEST,userLabel,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.WEST,passLabel,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.EAST,signUp,190,SpringLayout.WEST,login);
        springLayout.putConstraint(SpringLayout.EAST,photo,175,SpringLayout.EAST,this);
        springLayout.putConstraint(SpringLayout.SOUTH,photo,240,SpringLayout.SOUTH,this);

        this.add(welcome);
        this.add(login);
        this.add(signUp);
        this.add(userLabel);
        this.add(passLabel);
        this.add(userText);
        this.add(passText);
        this.add(photo);
        this.setVisible(true);

    }

}