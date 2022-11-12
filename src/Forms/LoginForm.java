package Forms;

import Launcher.FormsID;
import Model.PasswordManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;

public class LoginForm implements Form {
    private JPanel parent;
    private JPanel mainPanel;

    private JPasswordField pleaseEnterYourMasterPasswordField;
    private JButton loginButton;
    private JLabel errorLabel;
    private JButton loadButton;
    private JButton backButton;
    private JLabel fileNameLabel;
    private Dictionary panels;
    private PasswordManagerModel model;
    private final String name = "LoginForm";
    private final Dimension size = new Dimension(350, 350);

    public LoginForm(JPanel parent, Dictionary panels){
        this.parent = parent;
        this.panels = panels;
        this.model = PasswordManagerModel.getInstance();


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) parent.getLayout();
                cl.show(parent,(String) panels.get(FormsID.MAINMENU.getID()));
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    @Override
    public String getCardName() {
        return name;
    }
}
