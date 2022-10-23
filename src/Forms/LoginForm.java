package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm{
    private JPanel loginPanel;
    private JPasswordField pleaseEnterYourMasterPasswordField;
    private JButton loginButton;
    private JLabel LabelLogin;
    private JTree tree1;

    public LoginForm(JFrame parent){

        parent.setTitle("Login");
        parent.setContentPane(loginPanel);
        parent.setMinimumSize(new Dimension(450,360));
        parent.setLocationRelativeTo(parent);
        parent.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        parent.setVisible(true);
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
