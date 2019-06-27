import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
    JButton SUBMIT;
    Panel panel;
    Label userLabel, passLabel;
    JTextField userText, passText;
    String userName;
    String passWord;
    Label welcome;
    Button login;
    Button signUp;
    Login() {
        setTitle("LOGIN FORM");
        welcome=new Label("Welcome to JPotify!!!",3);
        login=new Button("Login",3);
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
        springLayout.putConstraint(SpringLayout.NORTH, userLabel, 30, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, userText, 30, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, passLabel, 100, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, passText, 100, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.WEST, userText, 30, SpringLayout.EAST, userLabel);
        springLayout.putConstraint(SpringLayout.WEST, passText, 30, SpringLayout.EAST, passLabel);
        springLayout.putConstraint(SpringLayout.NORTH, signUp, 30, SpringLayout.SOUTH, passLabel);
        springLayout.putConstraint(SpringLayout.NORTH, login, 30, SpringLayout.SOUTH, passText);
        springLayout.putConstraint(SpringLayout.WEST,login,70,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.WEST,userLabel,10,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.WEST,passLabel,10,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.EAST,signUp,200,SpringLayout.WEST,login);
        this.add(login);
        this.add(signUp);
        this.add(userLabel);
        this.add(passLabel);
        this.add(userText);
        this.add(passText);
        this.setVisible(true);

    }


//    public static void main(String[] args) {
//        Login login=new Login();
//    }
}