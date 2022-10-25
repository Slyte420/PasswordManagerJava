package Forms;

import javax.swing.*;
import java.awt.*;

public class LoginForm{
    private JPanel mainPanel;
    private JPasswordField pleaseEnterYourMasterPasswordField;
    private JButton loginButton;
    private JLabel LabelLogin;
    private JButton loadButton;
    private JButton backButton;

    private final Dimension size = new Dimension(350,350);
    public LoginForm(JFrame parent){

        parent.setContentPane(mainPanel);

    }

}
