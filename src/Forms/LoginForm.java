package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm{
    private JPanel mainPanel;
    private JPasswordField pleaseEnterYourMasterPasswordField;
    private JButton loginButton;
    private JLabel LabelLogin;
    private JTree tree1;

    public LoginForm(JFrame parent){

        parent.setContentPane(mainPanel);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LabelLogin.setText("Wrong Password");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
